package net.mieczkowski.singleactivity.controllers.baseControllers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import net.mieczkowski.singleactivity.interfaces.*


/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
abstract class BaseController : Controller {

    var context: Context? = applicationContext

    val iToolbar: IToolbar by lazy { activity as IToolbar }
    val iCollapsingToolbar: ICollapsingToolbar by lazy { activity as ICollapsingToolbar }
    val iTabsLayout: ITabsLayout by lazy { activity as ITabsLayout }

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    abstract fun getLayoutID(): Int

    abstract fun onViewCreated(view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(getLayoutID(), container, false)
        onViewCreated(view)

        if (this is ICollapse) {
            iCollapsingToolbar.setCollapseController(getCollapseController())
        } else {
            iCollapsingToolbar.setCollapseController(null)
        }

        if (this is ITabs) {
            iTabsLayout.showTabs(getMViewPager(), getBasePageAdapter())
        } else {
            iTabsLayout.hideTabs()
        }

        return view
    }

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        this.context = context
    }

    override fun onContextUnavailable() {
        super.onContextUnavailable()
        this.context = null
    }

    fun setTitle(title: String) {
        iCollapsingToolbar.setCollapsingTitle()
        iToolbar.getMSupportActionBar().title = title
    }

    fun setTitle(title: Int) {
        val titleStr = resources!!.getString(title)
        setTitle(titleStr)
    }

}