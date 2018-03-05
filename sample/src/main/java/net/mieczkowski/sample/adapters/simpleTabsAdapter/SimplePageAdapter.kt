package net.mieczkowski.singleactivity.adapters.simpleTabsAdapter

import com.bluelinelabs.conductor.Controller
import net.mieczkowski.singleactivity.adapters.baseAdapters.BasePageAdapter

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
class SimplePageAdapter(host: Controller) : BasePageAdapter(host) {

    override fun getNewController(position: Int): Controller {
        return SimpleTabController()
    }

    override fun getCount(): Int = 5

    override fun getPageTitle(position: Int): CharSequence {
        return position.toString()
    }
}