package net.mieczkowski.singleactivity.controllers.baseControllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
abstract class BaseChildController : BaseController {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(getLayoutID(), container, false)
        onViewCreated(view)

        return view
    }
}