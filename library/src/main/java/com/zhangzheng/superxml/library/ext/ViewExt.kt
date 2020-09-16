package com.zhangzheng.superxml.library.ext

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View



fun View.setRadius(radius: Float) {
    val backgroundColor = getBackgroundColor(this)
    if (backgroundColor != null) {
        setRoundRectBgByColorValue(this,backgroundColor, radius)
    }
}

private fun setRoundRectBgByColorValue(view: View, color: Int, radius: Float) {
    val backgroundDrawable = GradientDrawable().also {
        it.shape = GradientDrawable.RECTANGLE
        it.cornerRadius = radius
        it.setColor(color)
    }
    view.background = backgroundDrawable
}


fun getBackgroundColor(view: View): Int? {
    var bgColor: Int? = null
    if (view.background is ColorDrawable) {
        val colorDrawable = view.background as ColorDrawable
        bgColor = colorDrawable.color
    }
    return bgColor
}
