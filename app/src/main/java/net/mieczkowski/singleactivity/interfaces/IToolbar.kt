package net.mieczkowski.singleactivity.interfaces

import android.support.design.widget.AppBarLayout
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
interface IToolbar {

    fun getMSupportActionBar(): ActionBar

    fun getToolbar(): Toolbar

    fun getDrawerLayout(): DrawerLayout

    fun getAppBarLayout(): AppBarLayout
}