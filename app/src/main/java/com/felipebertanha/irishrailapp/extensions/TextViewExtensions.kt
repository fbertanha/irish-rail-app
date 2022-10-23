package com.felipebertanha.irishrailapp.extensions

import android.widget.TextView

/**
 * Created by felipebertanha on 23/October/2022
 */
object TextViewExtensions {

    fun TextView.setStringFromResource(resId: Int, vararg formatArgs: Any) {
        this.text = context.getString(resId, *formatArgs)
    }
}