package com.zhangzheng.superxml.library.decorate.wrap.coverchildren

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class ImageViewCoverParse : AbsChildViewParse<ImageView>() {

    override fun createInfoView(context: Context, attributeSet: AttributeSet?) = ImageView(context,attributeSet)

    override fun coverAttribute(): MutableList<*> = mutableListOf(
        AttributeInfo("src",{ drawable }) { drawable -> setImageDrawable(drawable) },
        AttributeInfo("scaleType",{ scaleType }) { scaleType -> setScaleType(scaleType) }
    )

}