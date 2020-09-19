package com.zhangzheng.superxml.library.decorate.wrap.coverchildren

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

 interface IChildViewParse {

     fun init(context: Context)

    fun parentAttribute(attributeSet: AttributeSet?)

    fun childAttribute(childViewAttr: AttributeSet?)

    fun updateChildView(view: View)
}

private val childViewParseList = mutableListOf<IChildViewParse>(
    TextViewCoverParse(),
    ImageViewCoverParse()
)

fun addCoverChildViewParse(parse:IChildViewParse){
    childViewParseList.add(parse)
}

class CoverChildrenLayout(var baseView: ViewGroup, attributeSet: AttributeSet?) : FrameLayout(baseView.context) {

    init {
        childViewParseList.forEach { it.init(context) }
        childViewParseList.forEach { it.parentAttribute(attributeSet) }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        baseView.addView(child, index, params)
        childViewParseList.forEach {
            if (child != null) {
                it.updateChildView(child)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as ViewGroup).addView(baseView, removeSelf())
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        childViewParseList.forEach { it.childAttribute(attrs) }
        return super.generateLayoutParams(attrs)
    }

    private fun removeSelf(): Int {
        val parent = parent as ViewGroup
        var index = 0
        for (i in 0 until parent.childCount) {
            if (parent.getChildAt(i) == this) {
                parent.removeView(this)
                break
            }
            index++
        }
        return index
    }
}