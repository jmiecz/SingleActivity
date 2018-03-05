package net.mieczkowski.singleactivity.controllers.baseControllers

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import net.mieczkowski.singleactivity.interfaces.IBack

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
abstract class BackController : BaseController, IBack {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    fun onBackClick(): Boolean = false

    override fun onViewCreated(view: View) {
        setBack()
    }

    private fun setBack() {
        val backIcon = ContextCompat.getDrawable(context, getBackIcon())

        iToolbar.getMSupportActionBar().apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(backIcon)
        }

        iToolbar.getToolbar().setNavigationOnClickListener {
            if (!onBackClick() && !handleBack()) {
                activity?.onBackPressed()
            }
        }
    }
}