package com.zhangzheng.superxml.library.ext

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View

fun View.setBorder(width: Int, color: Int) {
    background = getBorderBg(this, width, color)
}


private fun getBorderBg(view: View, width: Int, color: Int): Drawable {
    if (view.background !is ColorDrawable && view.background !is GradientDrawable) {
        return view.background
    }

    val drawable = if (view.background is GradientDrawable) {
        view.background as GradientDrawable
    } else GradientDrawable().also {
        it.color = ColorStateList.valueOf((view.background as ColorDrawable).color)
    }

    return drawable.also {
        it.setStroke(width, color)
    }
}