package com.zhangzheng.superxml.library.decorate

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.view.View
import android.widget.TextView
import com.zhangzheng.superxml.library.R


class TextColorPresenterDecorate(var color: ColorStateList? = null) : IDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        val pressed = typedArray.getColor(
            R.styleable.decorate_view_layout_textColor_pressedTrue,0)
        val normal = typedArray.getColor(
            R.styleable.decorate_view_layout_textColor_pressedFalse,0)
        if (pressed == 0 || normal == 0) {
            return false
        }

        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_pressed)
        states[1] = intArrayOf(-android.R.attr.state_pressed)
        color = ColorStateList(states, intArrayOf(pressed,normal))


        return true
    }

    override fun decorate(view: View) {
        if(view is TextView){
            view.setTextColor(color)
        }
    }

}