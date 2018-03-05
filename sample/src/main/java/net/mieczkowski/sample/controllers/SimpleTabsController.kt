package net.mieczkowski.singleactivity.controllers

import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.simple_tabs_layout.view.*
import net.mieczkowski.sample.R
import net.mieczkowski.singleactivity.adapters.baseAdapters.BasePageAdapter
import net.mieczkowski.singleactivity.adapters.simpleTabsAdapter.SimplePageAdapter
import net.mieczkowski.singleactivity.controllers.baseControllers.BackController
import net.mieczkowski.singleactivity.interfaces.ITabs

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
class SimpleTabsController : BackController, ITabs {

    lateinit var viewPager: ViewPager

    constructor() : super()

    override fun getLayoutID(): Int = R.layout.simple_tabs_layout

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        setTitle("Tabs")
        viewPager = view.viewPager
    }

    override fun getMViewPager(): ViewPager = viewPager

    override fun getBasePageAdapter(): BasePageAdapter {
        return SimplePageAdapter(this)
    }
}