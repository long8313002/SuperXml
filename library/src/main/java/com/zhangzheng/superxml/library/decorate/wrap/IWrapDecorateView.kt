package com.zhangzheng.superxml.library.decorate.wrap

import android.view.View
import com.zhangzheng.superxml.library.decorate.IDecorateView

abstract class IWrapDecorateView :
    IDecorateView(){

    override fun decorate(view: View) =Unit

    abstract fun decorateView(view: View):View
}