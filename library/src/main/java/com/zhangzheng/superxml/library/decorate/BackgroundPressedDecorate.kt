package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.graphics.drawable.StateListDrawable
import android.view.View
import com.zhangzheng.superxml.library.R
import com.zhangzheng.superxml.library.ext.createRadiusDrawable


internal class BackgroundPressedDecorate(var drawable: StateListDrawable? = null) :
    IDecorateView() {


    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        val radius = typedArray.getDimension(R.styleable.decorate_view_layout_radius, 0f)

        val press = createRadiusDrawable(
            radius, typedArray.getDrawable(
                R.styleable.decorate_view_layout_background_pressedTrue
            )
        )
        val normal = createRadiusDrawable(
            radius, typedArray.getDrawable(
                R.styleable.decorate_view_layout_background_pressedFalse
            )
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