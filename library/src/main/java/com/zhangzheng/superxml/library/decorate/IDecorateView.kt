package com.zhangzheng.superxml.library.decorate

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View


abstract class IDecorateView {

    var hasExtraInfo: Boolean = false
    var attributeSet: AttributeSet? = null

    /**
     * 初始化解析额外添加的信息
     * @return 是否包含额外信息
     */
    abstract fun initExtraInfo(typedArray: TypedArray): Boolean

    abstract fun decorate(view: View)
}