package com.zhangzheng.superxml.library.decorate.wrap

import android.content.res.TypedArray
import android.view.View
import android.view.ViewGroup
import com.zhangzheng.superxml.library.R
import com.zhangzheng.superxml.library.decorate.wrap.coverchildren.CoverChildrenLayout

class CoverChildrenWrapDecorate : IWrapDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray) =
        typedArray.getBoolean(R.styleable.decorate_view_layout_cover_children, false)

    override fun decorateView(view: View) =
        if (view !is ViewGroup) {
            view
        } else {
            CoverChildrenLayout(view, attributeSet)
        }


}