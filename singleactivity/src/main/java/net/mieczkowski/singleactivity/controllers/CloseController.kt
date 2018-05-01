package net.mieczkowski.singleactivity.controllers.baseControllers

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import net.mieczkowski.singleactivity.interfaces.IClose

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
abstract class CloseController : BaseController, IClose {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    fun onCloseClick(): Boolean = false

    override fun onViewCreated(view: View) {
        setClose()
    }

    private fun setClose() {
        val closeIcon = ContextCompat.getDrawable(context, getCloseIcon())

        iToolbar.getMSupportActionBar().apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(closeIcon)
        }

        iToolbar.getToolbar().setNavigationOnClickListener {
            if (!onCloseClick() && !handleBack()) {
                activity?.onBackPressed()
            }
        }
    }

}