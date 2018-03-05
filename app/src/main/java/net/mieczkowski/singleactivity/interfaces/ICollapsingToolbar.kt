package net.mieczkowski.singleactivity.interfaces

import android.support.annotation.ColorInt
import net.mieczkowski.singleactivity.controllers.baseControllers.BaseCollapseController

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
interface ICollapsingToolbar {

    fun setCollapseController(controller: BaseCollapseController? = null)

    fun setCollapsingTitle(title: String? = null, @ColorInt titleColor: Int? = null)

    fun setContentScrimColor(@ColorInt color: Int)

    fun setBarScrimColor(@ColorInt color: Int)
}