package com.zhangzheng.superxml.library.decorate.wrap.coverchildren

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import java.lang.Exception

abstract class AbsChildViewParse<T : View> : IChildViewParse {

    inner  class AttributeInfo<M>(var getAttr: T.() -> M, var setAttr: T.(value: M) -> Unit)

    private lateinit var infoView: T
    private var defaultInfoView: T? = null
    private val attribute = attribute()

    override fun parse(context: Context, attributeSet: AttributeSet?) {
        infoView = createInfoView(context, attributeSet)
        if (defaultInfoView == null) {
            defaultInfoView = defaultInfoView(context)
        }

    }

    abstract fun createInfoView(context: Context, attributeSet: AttributeSet?): T

    abstract fun attribute(): Array<*>

    override fun updateChildView(childView: View) {
        if(!checkView(childView)){
            return
        }

        val defaultChildView = defaultChildView(childView)

        attribute.forEach {
            it as AttributeInfo<Any>
            val infoViewValue = it.getAttr(infoView)
            val defaultInfoViewValue = it.getAttr(defaultInfoView!!)
            val value = it.getAttr(childView as T)
            val defaultValue = it.getAttr(defaultChildView as T)

            if(infoViewValue==defaultInfoViewValue){
                return
            }
            if(value!=defaultValue){
                return
            }
            it.setAttr(childView, infoViewValue)
        }
    }

    private fun checkView(view: View) = try {
        view as T
        true
    }catch (e:Exception){
        false
    }

    private fun defaultInfoView(context: Context) = createInfoView(context, null)

    private fun defaultChildView(view: View) =
        view.javaClass.getConstructor(Context::class.java).newInstance(view.context)


}

private interface IChildViewParse {
    fun parse(context: Context, attributeSet: AttributeSet?)

    fun updateChildView(view: View)
}

private val childViewParseList = arrayListOf<IChildViewParse>(
    TextViewCoverParse()
)

class CoverChildrenLayout(var baseView: ViewGroup, attributeSet: AttributeSet?) :
    FrameLayout(baseView.context) {

    init {
        childViewParseList.forEach {
            it.parse(context, attributeSet)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        baseView.addView(child, index, params)
        childViewParseList.forEach {
            if (child != null) {
                it.updateChildView(child)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as ViewGroup).addView(baseView, removeSelf())
    }

    private fun removeSelf(): Int {
        val parent = parent as ViewGroup
        var index = 0
        for (i in 0 until parent.childCount) {
            if (parent.getChildAt(i) == this) {
                parent.removeView(this)
                break
            }
            index++
        }
        return index
    }


}