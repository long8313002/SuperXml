package com.zhangzheng.superxml.library.decorate.wrap.coverchildren

import android.content.Context
import android.util.AttributeSet
import android.view.View

abstract class AbsChildViewParse<T : View> : IChildViewParse {

    inner class AttributeInfo<M>(
        var name: String,
        var getAttr: T.() -> M,
        var setAttr: T.(value: M) -> Unit
    )

    private lateinit var infoView: T
    private lateinit var coverAttribute: MutableList<*>
    private lateinit var updateAttribute: MutableList<*>
    protected lateinit var context:Context

    override fun init(context: Context) {
        this.context = context
    }

    override fun parentAttribute(attributeSet: AttributeSet?) {
        infoView = createInfoView(context, attributeSet)
        coverAttribute = coverAttribute()
        filterAttribute(coverAttribute,attributeSet, false)
    }

    private fun filterAttribute(attributeList: MutableList<*>, attributeSet: AttributeSet?, hasAttrFilter: Boolean) {
        val iterator = attributeList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next() as AttributeInfo<Any>
            val name = item.name
            if ((hasAttrFilter && hasAttribute(attributeSet, name))
                || (!hasAttrFilter && !hasAttribute(attributeSet, name))
            ) {
                iterator.remove()
            }
        }
    }

    private fun hasAttribute(attributeSet: AttributeSet?, name: String): Boolean {
        val nameSpace = "http://schemas.android.com/apk/res/android"
        return attributeSet?.getAttributeValue(nameSpace, name)?.isNotEmpty() == true
    }

    override fun childAttribute(childViewAttr: AttributeSet?) {
        updateAttribute = ArrayList(coverAttribute)
        filterAttribute(updateAttribute,childViewAttr, true)
    }

    abstract fun createInfoView(context: Context, attributeSet: AttributeSet?): T

    abstract fun coverAttribute(): MutableList<*>

    override fun updateChildView(childView: View) {
        updateAttribute.forEach {
            try {
                it as AttributeInfo<Any>
                val value = it.getAttr(infoView)
                it.setAttr(childView as T, value)
            }catch (e:ClassCastException){

            }
        }
    }


}