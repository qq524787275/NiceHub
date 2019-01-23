package com.zhuzichu.mvvm

import android.app.Application
import android.content.Context
import com.zhuzichu.mvvm.global.font.FontConfig
import com.zhuzichu.mvvm.global.font.Zh

open class App : Application() {
    companion object {
        var context: Application? = null
        fun getContext(): Context {
            return context!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = this
        FontConfig.initFont(Zh())
    }
}