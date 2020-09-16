package com.zhangzheng.superxml.library.decorate

import android.view.View

abstract class IWrapDecorateView :IDecorateView(){

    override fun decorate(view: View) =Unit

    abstract fun decorateView(view: View):View
}