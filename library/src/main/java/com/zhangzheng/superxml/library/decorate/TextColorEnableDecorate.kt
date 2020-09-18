package com.zhangzheng.superxml.library.decorate

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.view.View
import android.widget.TextView
import com.zhangzheng.superxml.library.R


internal class TextColorEnableDecorate(var color: ColorStateList? = null) : IDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        val enable = typedArray.getColor(
            R.styleable.decorate_view_layout_textColor_enableTrue,0)
        val normal = typedArray.getColor(
            R.styleable.decorate_view_layout_textColor_enableFalse,0)
        if (enable == 0 || normal == 0) {
            return false
        }

        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_enabled)
        states[1] = intArrayOf(-android.R.attr.state_enabled)
        color = ColorStateList(states, intArrayOf(enable,normal))

        return true
    }

    override fun decorate(view: View) {
        if(view is TextView){
            view.setTextColor(color)
        }
    }

}