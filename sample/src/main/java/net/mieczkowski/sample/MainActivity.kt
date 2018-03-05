package net.mieczkowski.sample

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.bluelinelabs.conductor.Controller
import net.mieczkowski.singleactivity.SingleActivity
import net.mieczkowski.singleactivity.controllers.SimpleMenuController

/**
 * Created by Josh Mieczkowski on 3/4/2018.
 */
class MainActivity : SingleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigationHeader(R.layout.nav_header_main)
    }

    override fun getStartingController(): Controller = SimpleMenuController()

    override fun getAdapterForNavigation(): RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
}