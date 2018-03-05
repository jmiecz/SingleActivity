package net.mieczkowski.singleactivity.controllers

import android.os.Bundle
import android.view.View
import net.mieczkowski.sample.R
import net.mieczkowski.singleactivity.controllers.baseControllers.BaseCollapseController

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
class SimpleCollapseController : BaseCollapseController {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    override fun getLayoutID(): Int = R.layout.simple_collapse_layout

    override fun getTitle(): String = "Close window"

    override fun onViewCreated(view: View) {

    }
}