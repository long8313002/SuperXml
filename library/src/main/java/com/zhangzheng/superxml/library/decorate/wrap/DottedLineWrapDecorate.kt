package com.zhangzheng.superxml.library.decorate.wrap

import android.content.res.TypedArray
import android.view.View
import com.zhangzheng.superxml.library.R
import com.zhangzheng.superxml.library.view.DottedLineView

class DottedLineWrapDecorate : IWrapDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray)=
         typedArray.getDimension(R.styleable.decorate_view_layout_dash_height, 0f) > 0
                && typedArray.getDimension(R.styleable.decorate_view_layout_dash_width, 0f) > 0


    override fun decorateView(view: View): View {
        return DottedLineView(view.context, attributeSet).also {
            it.id = view.id
        }
    }


}