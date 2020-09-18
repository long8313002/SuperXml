package com.zhangzheng.superxml.library

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import com.zhangzheng.superxml.library.decorate.*
import com.zhangzheng.superxml.library.decorate.RadiusDecorate
import com.zhangzheng.superxml.library.decorate.SrcRadiusDecorate
import com.zhangzheng.superxml.library.decorate.wrap.DottedLineWrapDecorate
import com.zhangzheng.superxml.library.decorate.wrap.IWrapDecorateView
import com.zhangzheng.superxml.library.decorate.wrap.ScrollWrapDecorate

internal object ViewDecorateManager {

    private val decorateList = arrayListOf(
        RadiusDecorate(),
        SrcRadiusDecorate(),
        BackgroundPressedDecorate(),
        BackgroundSelectedDecorate(),
        BackgroundEnableDecorate(),
        TextColorEnableDecorate(),
        TextColorPresenterDecorate(),
        TextColorSelectedDecorate(),
        BorderDecorate()
    )

    private val wrapDecorateList = arrayListOf(
        ScrollWrapDecorate(),
        DottedLineWrapDecorate()
    )

    fun addDecorate(decorate: IWrapDecorateView){
        wrapDecorateList.add(decorate)
    }

    fun addDecorate(decorate:IDecorateView){
        decorateList.add(decorate)
    }


    fun hasDecorate(context: Context, attributeSet: AttributeSet): Boolean {
        val typedArray: TypedArray = context.obtainStyledAttributes(
            attributeSet, R.styleable.decorate_view
        )

        var hasDecorateInfo = false
        (decorateList + wrapDecorateList).forEach {
            it.attributeSet = attributeSet
            if (it.initExtraInfo(typedArray)) {
                hasDecorateInfo = true
                it.hasExtraInfo = true
            } else {
                it.hasExtraInfo = false
            }
        }

        typedArray.recycle()

        return hasDecorateInfo
    }

    fun decorate(view: View): View {

        decorateList.forEach {
            if (it.hasExtraInfo) {
                it.decorate(view)
            }
        }

        var wrapView = view
        wrapDecorateList.forEach {
            if (it.hasExtraInfo) {
                wrapView = it.decorateView(wrapView)
            }
        }
        return wrapView
    }
}