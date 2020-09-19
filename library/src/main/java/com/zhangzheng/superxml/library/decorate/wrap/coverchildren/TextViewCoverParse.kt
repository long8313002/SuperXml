package com.zhangzheng.superxml.library.decorate.wrap.coverchildren

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class TextViewCoverParse : AbsChildViewParse<TextView>() {

    override fun createInfoView(context: Context, attributeSet: AttributeSet?): TextView =
        TextView(context, attributeSet)

    override fun attribute(): Array<*> = arrayOf(
        AttributeInfo({ textSize }) { value -> textSize = value },
        AttributeInfo({ textColors }) { value -> setTextColor(value) },
        AttributeInfo({ text }) { text -> setText(text) }
    )


}