package com.zhangzheng.superxml.demo

import android.app.Application
import com.zhangzheng.superxml.library.SuperXml

class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SuperXml.init(this)
    }
}