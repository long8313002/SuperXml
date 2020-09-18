package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.view.View
import com.zhangzheng.superxml.library.R
import com.zhangzheng.superxml.library.ext.setBorder

class BorderDecorate : IDecorateView() {

    private var borderColor = 0
    private var borderWidth = 0

    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        borderWidth = typedArray.getDimension(
            R.styleable.decorate_view_layout_border_width, 0f
        ).toInt()

        borderColor = typedArray.getColor(
            R.styleable.decorate_view_layout_border_color, 0
        )

        return borderWidth != 0 && borderColor != 0
    }

    override fun decorate(view: View) {

        view.setBorder(borderWidth,borderColor)
    }

}