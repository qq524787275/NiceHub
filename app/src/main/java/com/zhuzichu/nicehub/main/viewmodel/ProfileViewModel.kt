package com.zhuzichu.nicehub.main.viewmodel

import android.app.Application
import android.view.View
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand
import com.zhuzichu.mvvm.global.theme.ThemeConfig

class ProfileViewModel(application: Application) : BaseViewModel(application) {

    val dark = BindingCommand<View>(BindingAction {
        ThemeConfig.initTheme(true)
    })

    val night = BindingCommand<View>(BindingAction {
        ThemeConfig.initTheme(false)
    })

}