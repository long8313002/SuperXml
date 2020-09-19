package com.zhangzheng.superxml.library

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.zhangzheng.superxml.library.decorate.IDecorateView
import com.zhangzheng.superxml.library.decorate.wrap.IWrapDecorateView
import com.zhangzheng.superxml.library.decorate.wrap.coverchildren.IChildViewParse
import com.zhangzheng.superxml.library.decorate.wrap.coverchildren.addCoverChildViewParse

object SuperXml {

    fun addDecorate(decorate: IWrapDecorateView) = ViewDecorateManager.addDecorate(decorate)

    fun addDecorate(decorate: IDecorateView) = ViewDecorateManager.addDecorate(decorate)

    fun addCoverAttributeParse(parse: IChildViewParse){
        addCoverChildViewParse(parse)
    }

    fun init(app: Application) {
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) = hookActivityLayout(p0)
            override fun onActivityPaused(p0: Activity) = Unit
            override fun onActivityStarted(p0: Activity) = Unit
            override fun onActivityDestroyed(p0: Activity) = Unit
            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) = Unit
            override fun onActivityStopped(p0: Activity) = Unit
            override fun onActivityResumed(p0: Activity) = Unit
        })
    }

    private fun hookActivityLayout(activity: Activity) {
        val layoutInflater = LayoutInflater.from(activity)
        setLayoutInflateAllowState(layoutInflater, true)
        layoutInflater.factory2 =
            LayoutInflateFactoryProxy(layoutInflater, object : LayoutInflateFactoryProxy.IService {
                override fun hasDecorate(attrs: AttributeSet) =
                    ViewDecorateManager.hasDecorate(activity, attrs)

                override fun decorate(view: View) = ViewDecorateManager.decorate(view)
            })
        setLayoutInflateAllowState(layoutInflater, false)
    }

    private fun setLayoutInflateAllowState(layoutInflater: LayoutInflater, isAllow: Boolean) {
        val mFactorySet = LayoutInflater::class.java.getDeclaredField("mFactorySet")
        mFactorySet.isAccessible = true
        mFactorySet.set(layoutInflater, !isAllow)
    }
}