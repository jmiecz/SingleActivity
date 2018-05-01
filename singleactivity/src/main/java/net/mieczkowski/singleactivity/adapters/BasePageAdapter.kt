package net.mieczkowski.singleactivity.adapters.baseAdapters

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
abstract class BasePageAdapter(host: Controller) : RouterPagerAdapter(host) {

    abstract fun getNewController(position: Int): Controller

    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(getNewController(position))
                    .tag(position.toString() + ""))
        }
    }

}