package net.mieczkowski.singleactivity.ext

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import net.mieczkowski.singleactivity.controllers.BaseDialogController

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */

fun Controller.show(controller: Controller?, pushChange: ControllerChangeHandler = HorizontalChangeHandler(), popChange: ControllerChangeHandler = HorizontalChangeHandler(), tag: String = javaClass.name) {
    show(controller?.router, pushChange, popChange, tag)
}

fun Controller.show(router: Router?, pushChange: ControllerChangeHandler = HorizontalChangeHandler(), popChange: ControllerChangeHandler = HorizontalChangeHandler(), tag: String = javaClass.name) {
    router?.let {
        if (!it.popToTag(tag)) {
            it.pushController(RouterTransaction.with(this)
                    .pushChangeHandler(pushChange)
                    .popChangeHandler(popChange)
                    .tag(tag))
        }
    }
}