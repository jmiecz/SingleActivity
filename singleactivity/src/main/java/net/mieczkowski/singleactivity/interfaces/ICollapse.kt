package net.mieczkowski.singleactivity.interfaces

import net.mieczkowski.singleactivity.controllers.baseControllers.BaseCollapseController

/**
 * Created by Josh Mieczkowski on 1/27/2018.
 */
interface ICollapse {

    fun getCollapseController(): BaseCollapseController
}