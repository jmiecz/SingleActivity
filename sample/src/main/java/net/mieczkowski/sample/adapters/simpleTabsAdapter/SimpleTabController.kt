package net.mieczkowski.singleactivity.adapters.simpleTabsAdapter

import android.view.View
import kotlinx.android.synthetic.main.simple_tab_layout.view.*
import net.mieczkowski.sample.R
import net.mieczkowski.singleactivity.controllers.baseControllers.BaseChildController
import net.mieczkowski.singleactivity.interfaces.ITab
import java.util.*

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
class SimpleTabController : BaseChildController, ITab {

    constructor() : super()

    override fun getLayoutID(): Int = R.layout.simple_tab_layout

    override fun onViewCreated(view: View) {

    }

    override fun onViewShowing() {
        view?.txtMessage?.text = Date().toString()
    }

}