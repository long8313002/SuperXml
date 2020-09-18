package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.graphics.drawable.StateListDrawable
import android.view.View
import com.zhangzheng.superxml.library.R


internal class BackgroundEnableDecorate(var drawable: StateListDrawable? = null) : IDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        val enable = typedArray.getDrawable(
            R.styleable.decorate_view_layout_background_enableTrue
        )
        val normal = typedArray.getDrawable(
            R.styleable.decorate_view_layout_background_enableFalse
        )
        if (enable == null || normal == null) {
            return false
        }

        drawable = StateListDrawable()
        drawable?.addState(intArrayOf(android.R.attr.state_enabled), enable)
        drawable?.addState(intArrayOf(-android.R.attr.state_enabled), normal)
        return true
    }

    override fun decorate(view: View) {
        view.background = drawable
    }

}