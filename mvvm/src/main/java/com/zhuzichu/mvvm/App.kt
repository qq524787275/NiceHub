package com.zhuzichu.mvvm

import android.app.Application
import android.content.Context
import com.zhuzichu.mvvm.global.language.LangConfig
import com.zhuzichu.mvvm.global.language.Zh
import com.zhuzichu.mvvm.global.theme.ThemeConfig

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
        LangConfig.initLang(Zh())
        ThemeConfig.initColorPrimary(resources.getColor(R.color.colorPrimary))
        ThemeConfig.initTheme(false)
    }
}