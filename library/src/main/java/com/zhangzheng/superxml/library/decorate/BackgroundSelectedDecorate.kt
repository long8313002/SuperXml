package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.graphics.drawable.StateListDrawable
import android.view.View
import com.zhangzheng.superxml.library.R
import com.zhangzheng.superxml.library.ext.createRadiusDrawable


internal class BackgroundSelectedDecorate(var drawable: StateListDrawable? = null) :
    IDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray): Boolean {

        val radius = typedArray.getDimension(R.styleable.decorate_view_layout_radius, 0f)

        val selected = createRadiusDrawable(
            radius, typedArray.getDrawable(
                R.styleable.decorate_view_layout_background_selectedTrue
            )
        )

        val normal = createRadiusDrawable(
            radius, typedArray.getDrawable(
                R.styleable.decorate_view_layout_background_selectedFalse
            )
        )

        if (selected == null || normal == null) {
            return false
        }

        drawable = StateListDrawable()
        drawable?.addState(intArrayOf(android.R.attr.state_selected), selected)
        drawable?.addState(intArrayOf(-android.R.attr.state_selected), normal)
        return true
    }

    override fun decorate(view: View) {
        view.background = drawable
    }

}