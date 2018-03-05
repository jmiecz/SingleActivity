package net.mieczkowski.singleactivity.controllers.baseControllers

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import net.mieczkowski.singleactivity.R
import net.mieczkowski.singleactivity.interfaces.IMenu

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
abstract class MenuController : BaseController, IMenu {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    override fun onViewCreated(view: View) {
        setToggle()
    }

    private fun setToggle() {
        iToolbar.getDrawerLayout().let {
            val toggle = ActionBarDrawerToggle(activity,
                    it,
                    iToolbar.getToolbar(),
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close)

            it.addDrawerListener(toggle)
            toggle.syncState()
        }
    }
}