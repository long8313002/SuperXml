package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.view.View
import com.zhangzheng.superxml.library.R
import com.zhangzheng.superxml.library.ext.setRadius

internal class RadiusDecorate(var radius: Float = 0f) : IDecorateView() {

    override fun initExtraInfo(typedArray: TypedArray): Boolean {
        radius = typedArray.getDimension(R.styleable.decorate_view_layout_radius,0f)
        return radius > 0
    }

    override fun decorate(view: View)= view.setRadius(radius)

}