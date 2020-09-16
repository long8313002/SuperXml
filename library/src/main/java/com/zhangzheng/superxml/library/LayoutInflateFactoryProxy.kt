package com.zhangzheng.superxml.library

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import java.lang.Exception



internal class LayoutInflateFactoryProxy(layoutInflater: LayoutInflater, private var service:IService ): LayoutInflater.Factory2 {

    interface IService {
        fun hasDecorate(attrs: AttributeSet): Boolean

        fun decorate(view: View)
    }

    private var factory = layoutInflater.factory
    private var factory2 = layoutInflater.factory2
    private var cloneLayoutInflate = layoutInflater.cloneInContext(layoutInflater.context)

    override fun onCreateView(parent: View?, name: String?, context: Context?,
        attrs: AttributeSet?): View? {

        if(attrs==null||!service.hasDecorate(attrs)){
            return null
        }

        var view = factory2.onCreateView(parent, name, context, attrs)
        if (view == null) {
            view = factory.onCreateView(name, context, attrs)
        }
        if (view == null) {
            view = createView(name, null, attrs)
        }
        if (view == null) {
            view = createView(name, "android.widget.", attrs)
        }
        if (view == null) {
            view = createView(name, "android.view.", attrs)
        }

        if (view != null) {
            service.decorate(view)
        }

        return view
    }

    private fun createView(name:String?,prefix:String?,attrs: AttributeSet?):View?{
        return try{
            cloneLayoutInflate.createView(name,prefix,attrs)
        }catch (e:Exception){
            null
        }
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?) =
        onCreateView(null, name, context, attrs)



}