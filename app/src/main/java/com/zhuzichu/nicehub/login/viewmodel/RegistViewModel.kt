package com.zhuzichu.nicehub.login.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand
import com.zhuzichu.mvvm.utils.toast

class RegistViewModel(application: Application) : BaseViewModel(application) {
    val username: MutableLiveData<String> = MutableLiveData()
    val code: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var nickname: MutableLiveData<String> = MutableLiveData()

    val regist = BindingCommand<View>(BindingAction {
        toast("regist")
    })

    val goForget = BindingCommand<View>(BindingAction {
        toast("goForget")
    })

    val goLogin = BindingCommand<View>(BindingAction {
        toast("goLogin")
    })

    val sendCode = BindingCommand<View>(BindingAction {
        toast("sendCode")
    })

}