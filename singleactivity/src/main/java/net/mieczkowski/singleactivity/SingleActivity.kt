package net.mieczkowski.singleactivity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.base_activity_layout.*
import kotlinx.android.synthetic.main.base_app_bar.*
import net.mieczkowski.singleactivity.adapters.baseAdapters.BasePageAdapter
import net.mieczkowski.singleactivity.controllers.baseControllers.BaseCollapseController
import net.mieczkowski.singleactivity.ext.inflateLayout
import net.mieczkowski.singleactivity.interfaces.ICollapsingToolbar
import net.mieczkowski.singleactivity.interfaces.ITab
import net.mieczkowski.singleactivity.interfaces.ITabsLayout
import net.mieczkowski.singleactivity.interfaces.IToolbar
import net.mieczkowski.singleactivity.listeners.OnControllerChangeListener
import java.util.concurrent.TimeUnit


/**
 * Created by Josh Mieczkowski on 3/4/2018.
 */
abstract class SingleActivity : AppCompatActivity(), IToolbar, ICollapsingToolbar, ITabsLayout {

    lateinit var router: Router
    lateinit var collapsingToolbarRouter: Router
    private var isCollapsible = true
    private var initExpand = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.base_activity_layout)
        setSupportActionBar(toolbar)

        getAppBarLayout().addOnOffsetChangedListener(object : AppBarStateChangeListener(){
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if(state == State.COLLAPSED){
                    if(!isCollapsible) {
                        (collapsingToolbar.layoutParams as AppBarLayout.LayoutParams).apply {
                            scrollFlags = 0
                        }
                        collapseCustomView.visibility = View.GONE

                    }else if(initExpand){
                        initExpand = false
                        Completable.timer(1, TimeUnit.MILLISECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    getAppBarLayout().setExpanded(true)
                                }
                    }
                }
            }

        })

        collapsingToolbarRouter = Conductor.attachRouter(this, collapseCustomView, savedInstanceState)
        router = Conductor.attachRouter(this, root, savedInstanceState)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(getStartingController()))
        }

        recyclerNav.layoutManager = LinearLayoutManager(this)
        recyclerNav.adapter = getAdapterForNavigation()
    }

    abstract fun getStartingController(): Controller

    fun setNavigationHeader(layoutID: Int){
        nav_view.addHeaderView(inflateLayout(layoutID))
    }

    abstract fun getAdapterForNavigation(): RecyclerView.Adapter<RecyclerView.ViewHolder>?

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun getMSupportActionBar(): ActionBar = supportActionBar!!

    override fun getToolbar(): Toolbar = toolbar

    override fun getDrawerLayout(): DrawerLayout = drawerLayout

    override fun getAppBarLayout(): AppBarLayout = app_bar

    override fun setCollapseController(controller: BaseCollapseController?) {
        if (controller != null) {
            (collapsingToolbar.layoutParams as AppBarLayout.LayoutParams).apply {
                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            }

            collapseCustomView.visibility = View.VISIBLE


            isCollapsible = true
            initExpand = true
            setCollapsingTitle(" ")
            collapsingToolbarRouter.setRoot(RouterTransaction.with(controller))
            getAppBarLayout().setExpanded(false, false)

        } else {
            isCollapsible = false
            getAppBarLayout().setExpanded(false)
            collapseCustomView.removeAllViews()
        }
    }

    override fun setCollapsingTitle(title: String?, titleColor: Int?) {
        if (title != null) {
            collapsingToolbar.title = title
            collapsingToolbar.isTitleEnabled = true

            titleColor?.let {
                collapsingToolbar.setExpandedTitleColor(it)
            }
        } else {
            collapsingToolbar.isTitleEnabled = false
        }
    }

    override fun setContentScrimColor(color: Int) {
        collapsingToolbar.setContentScrimColor(color)
    }

    override fun setBarScrimColor(color: Int) {
        collapsingToolbar.setStatusBarScrimColor(color)
    }

    override fun showTabs(viewPager: ViewPager, basePagerAdapter: BasePageAdapter) {
        tabs.visibility = View.VISIBLE

        viewPager.adapter = basePagerAdapter
        viewPager.addOnPageChangeListener(OnControllerChangeListener(basePagerAdapter))
        tabs.setupWithViewPager(viewPager)

        Completable.timer(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val position = viewPager.currentItem
                    val controller = basePagerAdapter.getRouter(position)?.getControllerWithTag(position.toString())
                    if (controller is ITab) {
                        controller.onViewShowing()
                    }
                }
    }

    override fun hideTabs() {
        tabs.visibility = View.GONE

        tabs.setupWithViewPager(null)
    }
}