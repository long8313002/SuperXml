package com.zhangzheng.superxml.library

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import com.zhangzheng.superxml.library.decorate.*
import com.zhangzheng.superxml.library.decorate.RadiusDecorate
import com.zhangzheng.superxml.library.decorate.SrcRadiusDecorate

internal object ViewDecorateManager {

    private val decorateList = arrayListOf(
        RadiusDecorate(),
        SrcRadiusDecorate(),
        BackgroundPressedDecorate(),
        BackgroundSelectedDecorate(),
        BackgroundEnableDecorate(),
        TextColorEnableDecorate(),
        TextColorPresenterDecorate(),
        TextColorSelectedDecorate()
    )

    fun hasDecorate(context: Context, attributeSet: AttributeSet): Boolean {
        val typedArray: TypedArray = context.obtainStyledAttributes(
            attributeSet, R.styleable.decorate_view
        )

        var hasDecorateInfo = false
        decorateList.forEach {
            if (it.initExtraInfo(typedArray)) {
                hasDecorateInfo = true
                it.hasExtraInfo = true
            }else{
                it.hasExtraInfo = false
            }
        }

        typedArray.recycle()

        return hasDecorateInfo
    }

    fun decorate(view: View) {

        decorateList.forEach {
            if(it.hasExtraInfo){
                it.decorate(view)
            }
        }

    }
}