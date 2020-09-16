package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.view.View

internal interface IDecorateView {

    /**
     * 初始化解析额外添加的信息
     * @return 是否包含额外信息
     */
    fun initExtraInfo(typedArray: TypedArray): Boolean

    fun decorate(view: View)
}