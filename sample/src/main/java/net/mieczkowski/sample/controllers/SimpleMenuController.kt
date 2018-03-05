package net.mieczkowski.singleactivity.controllers

import android.view.View
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import kotlinx.android.synthetic.main.simple_menu_layout.view.*
import net.mieczkowski.sample.R
import net.mieczkowski.singleactivity.controllers.baseControllers.MenuController
import net.mieczkowski.singleactivity.ext.show

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
class SimpleMenuController : MenuController() {

    override fun getLayoutID(): Int = R.layout.simple_menu_layout

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        setTitle("Main Menu")

        view.btnShowBack.setOnClickListener {
            SimpleBackController("1").show(this)
        }

        view.btnShowClose.setOnClickListener {
            SimpleCloseController().show(this, VerticalChangeHandler(), HorizontalChangeHandler())
        }

        view.btnShowTabs.setOnClickListener {
            SimpleTabsController().show(this)
        }
    }

}