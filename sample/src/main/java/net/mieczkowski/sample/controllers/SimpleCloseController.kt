package net.mieczkowski.singleactivity.controllers

import android.os.Bundle
import android.view.View
import net.mieczkowski.sample.R
import net.mieczkowski.singleactivity.controllers.baseControllers.BaseCollapseController
import net.mieczkowski.singleactivity.controllers.baseControllers.CloseController
import net.mieczkowski.singleactivity.interfaces.ICollapse

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
private const val MESSAGE_TAG = "messageTag"

class SimpleCloseController : CloseController(), ICollapse {

    override fun getLayoutID(): Int = R.layout.simple_close_layout

    override fun getCollapseController(): BaseCollapseController = SimpleCollapseController()
}