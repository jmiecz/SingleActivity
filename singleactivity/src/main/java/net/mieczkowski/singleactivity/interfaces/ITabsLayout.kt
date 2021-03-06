package net.mieczkowski.singleactivity.interfaces

import android.support.v4.view.ViewPager
import net.mieczkowski.singleactivity.adapters.baseAdapters.BasePageAdapter


/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
interface ITabsLayout {

    fun showTabs(viewPager: ViewPager, basePagerAdapter: BasePageAdapter)

    fun hideTabs()
}