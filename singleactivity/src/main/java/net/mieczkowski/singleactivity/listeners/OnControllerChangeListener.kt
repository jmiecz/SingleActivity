package net.mieczkowski.singleactivity.listeners

import android.support.v4.view.ViewPager
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import net.mieczkowski.singleactivity.interfaces.ITab

/**
 * Created by Josh Mieczkowski on 3/4/2018.
 */
class OnControllerChangeListener(private val routerPagerAdapter: RouterPagerAdapter) : ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        routerPagerAdapter.getRouter(position)?.let {
            val controller = it.getControllerWithTag(position.toString())

            if (controller is ITab) {
                controller.onViewShowing()
            }
        }
    }
}