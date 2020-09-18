package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.graphics.drawable.StateListDrawable
import android.view.View
import com.zhangzheng.superxml.library.R


internal class BackgroundPressedDecorate(var drawable: StateListDrawable? = null) : IDecorateView() {


    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        val press = typedArray.getDrawable(
            R.styleable.decorate_view_layout_background_pressedTrue
        )
        val normal = typedArray.getDrawable(
            R.styleable.decorate_view_layout_background_pressedFalse
        )
        if (press == null || normal == null) {
            return false
        }

        drawable = StateListDrawable()
        drawable?.addState(intArrayOf(android.R.attr.state_pressed), press)
        drawable?.addState(intArrayOf(-android.R.attr.state_pressed), normal)
        return true
    }

    override fun decorate(view: View) {
        view.background = drawable
    }

}