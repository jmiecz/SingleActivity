package net.mieczkowski.singleactivity.ext

import android.content.Context
import android.view.LayoutInflater
import android.view.View

/**
 * Created by Josh Mieczkowski on 3/4/2018.
 */

fun Context.inflateLayout(layoutID: Int): View =
        LayoutInflater.from(this).inflate(layoutID, null, false)
