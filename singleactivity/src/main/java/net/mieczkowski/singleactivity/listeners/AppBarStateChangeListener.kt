package net.mieczkowski.singleactivity.listeners

import android.support.design.widget.AppBarLayout

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
abstract class AppBarStateChangeListener: AppBarLayout.OnOffsetChangedListener {

    enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private var mCurrentState = State.IDLE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        mCurrentState = if (Math.abs(verticalOffset)-appBarLayout.totalScrollRange == 0)        {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED)
            }

            State.COLLAPSED
        }
        else{
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED)
            }

            State.EXPANDED
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
}