package com.zhangzheng.superxml.library.decorate

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.view.View
import android.widget.TextView
import com.zhangzheng.superxml.library.R


internal class TextColorSelectedDecorate(var color: ColorStateList? = null) : IDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        val selected = typedArray.getColor(
            R.styleable.decorate_view_layout_textColor_selectedTrue,0)
        val normal = typedArray.getColor(
            R.styleable.decorate_view_layout_textColor_selectedFalse,0)
        if (selected == 0 || normal == 0) {
            return false
        }

        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_selected)
        states[1] = intArrayOf(-android.R.attr.state_selected)
        color = ColorStateList(states, intArrayOf(selected,normal))

        return true
    }

    override fun decorate(view: View) {
        if(view is TextView){
            view.setTextColor(color)
        }
    }

}