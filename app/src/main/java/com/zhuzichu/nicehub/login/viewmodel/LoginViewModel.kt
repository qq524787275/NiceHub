package com.zhuzichu.nicehub.login.viewmodel

import android.app.Application
import android.view.View
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginViewModel(application: Application) : BaseViewModel(application) {


    val login: BindingCommand<View> = BindingCommand(BindingAction { val a=10/0; })
}