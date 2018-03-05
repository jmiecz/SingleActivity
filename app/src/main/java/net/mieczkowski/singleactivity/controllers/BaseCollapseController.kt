package net.mieczkowski.singleactivity.controllers.baseControllers

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.mieczkowski.singleactivity.listeners.AppBarStateChangeListener


/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
abstract class BaseCollapseController : BaseController {

    var isVisible = false

    val appBarStateChangeListener = object : AppBarStateChangeListener() {
        override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {

            when (state) {
                State.EXPANDED -> {
                    iCollapsingToolbar.setCollapsingTitle(" ")
                    isVisible = true
                }
                State.COLLAPSED -> {
                    if(isVisible) {
                        iCollapsingToolbar.setCollapsingTitle(getTitle())
                    }
                }
                else -> {
                    iCollapsingToolbar.setCollapsingTitle(" ")
                }
            }
        }
    }

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    open fun getTitle(): String = " "

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(getLayoutID(), container, false)
        iCollapsingToolbar.setCollapsingTitle(null)

        iToolbar.getAppBarLayout().addOnOffsetChangedListener(appBarStateChangeListener)

        onViewCreated(view)

        return view
    }

    override fun onDestroyView(view: View) {
        iToolbar.getAppBarLayout().removeOnOffsetChangedListener(appBarStateChangeListener)

        super.onDestroyView(view)
    }
}