package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.zhangzheng.superxml.library.R

private class ScrollViewProxy(val view: View,attributeSet: AttributeSet?) : ScrollView(view.context,attributeSet) {

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child?.parent != null) {
            return
        }
        if (child == view) {
            super.addView(child, index, params)
        } else if (view is ViewGroup) {
            view.addView(child, index, params)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        addView(view)
        id = R.id.decorateScrollView
    }
}


internal class ScrollWrapDecorate(var canScroll: Boolean = false) : IWrapDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        canScroll = typedArray.getBoolean(R.styleable.decorate_view_layout_canScroll, false)
        return canScroll
    }

    override fun decorateView(view: View): View {
        return ScrollViewProxy(view,attributeSet)
    }


}