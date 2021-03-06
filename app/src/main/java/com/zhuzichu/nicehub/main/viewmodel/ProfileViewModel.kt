package com.zhuzichu.nicehub.main.viewmodel

import android.app.Application
import android.view.View
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand
import com.zhuzichu.mvvm.bus.Event
import com.zhuzichu.mvvm.bus.RxBus
import com.zhuzichu.mvvm.global.theme.ThemeConfig
import com.zhuzichu.nicehub.R

class ProfileViewModel(application: Application) : BaseViewModel(application) {

    val dark = BindingCommand<View>(BindingAction {
        ThemeConfig.initTheme(true)
        RxBus.default.post(Event.ThemeChangeEvent())
    })

    val night = BindingCommand<View>(BindingAction {
        ThemeConfig.initTheme(false)
        RxBus.default.post(Event.ThemeChangeEvent())
    })

    val start = BindingCommand<View>(BindingAction {
        startFragment(R.id.profileFragment2)
    })
}