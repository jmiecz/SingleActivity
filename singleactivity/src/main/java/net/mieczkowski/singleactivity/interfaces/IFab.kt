package net.mieczkowski.singleactivity.interfaces

import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.view.View

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
interface IFab {
    fun setIcon(@DrawableRes drawableRes: Int)

    fun setBackgroundColor(@ColorInt color: Int)

    fun setRippleColor(@ColorInt color: Int)

    fun setOnClickListener(onClickListener: View.OnClickListener?)

    fun showFab()

    fun hideFab()

    fun setAnchor(view: View?, gravity: Int)

    fun setBottomMargin(sizeInPixel: Int?)
}