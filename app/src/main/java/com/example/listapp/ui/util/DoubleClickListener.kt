package com.example.listapp.ui.util

import android.view.View

class DoubleClickListener(private var action: View.OnClickListener): View.OnClickListener {
    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < 500) {
            lastClickTime = 0
            action.onClick(v)
            return
        }
        lastClickTime = clickTime
    }
}