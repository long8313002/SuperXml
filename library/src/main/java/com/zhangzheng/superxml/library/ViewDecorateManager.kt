package com.zhangzheng.superxml.library

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import com.zhangzheng.superxml.library.decorate.IDecorateView
import com.zhangzheng.superxml.library.decorate.RadiusDecorate

internal object ViewDecorateManager {

    private val decorateList = arrayListOf<IDecorateView>(
        RadiusDecorate()
    )

    fun hasDecorate(context: Context, attributeSet: AttributeSet): Boolean {
        val typedArray: TypedArray = context.obtainStyledAttributes(
            attributeSet, R.styleable.decorate_view
        )

        var hasDecorateInfo = false
        decorateList.forEach {
            if (it.initExtraInfo(typedArray)) {
                hasDecorateInfo = true
            }
        }

        typedArray.recycle()

        return hasDecorateInfo
    }

    fun decorate(view: View) {

        decorateList.forEach {
            it.decorate(view)
        }

    }
}