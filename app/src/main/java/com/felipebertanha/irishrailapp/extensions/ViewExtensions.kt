package com.felipebertanha.irishrailapp.extensions

import android.view.View

/**
 * Created by felipebertanha on 23/October/2022
 */
object ViewExtensions {

    fun View.showOrHide(shouldShow : Boolean) {
        if(shouldShow) show() else hide()
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide(gone: Boolean = true) {
        this.visibility = if (gone) View.GONE else View.INVISIBLE
    }
}