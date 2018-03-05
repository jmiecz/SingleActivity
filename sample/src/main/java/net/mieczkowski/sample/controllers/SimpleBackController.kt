package net.mieczkowski.singleactivity.controllers

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.simple_back_layout.view.*
import net.mieczkowski.sample.R
import net.mieczkowski.singleactivity.controllers.baseControllers.BackController
import net.mieczkowski.singleactivity.ext.show


/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
private const val MESSAGE_TAG = "messageTag"

class SimpleBackController : BackController {

    val message: String by lazy {
        args.getString(MESSAGE_TAG)
    }

    constructor(message: String) : super(Bundle().apply { putString(MESSAGE_TAG, message) })
    constructor(args: Bundle?) : super(args)

    override fun getLayoutID(): Int = R.layout.simple_back_layout

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        setTitle(message)

        view.btnBackToRoot.setOnClickListener {
            router.popToRoot()
        }

        view.btnShowBack.setOnClickListener {
            val number = message.toInt() + 1
            SimpleBackController(number.toString()).show(this, tag = number.toString())
        }

        view.btnGoBackTo.setOnClickListener {
            val string = view.editGoBackTo.text.toString()

            if (string.isNotBlank()) {
                val number = view.editGoBackTo.text.toString().toInt()

                val tag = if (number == 1) javaClass.name else number.toString()
                router.popToTag(tag)
            }
        }
    }
}