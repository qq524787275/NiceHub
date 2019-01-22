package com.zhuzichu.nicehub.login.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.orhanobut.logger.Logger
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginViewModel(application: Application) : BaseViewModel(application) {
    inner class UIChangeObservable {
        //密码开关观察者
        var showUsernameError = ObservableBoolean(false)
        var showPasswordError = ObservableBoolean(false)
    }
    val username: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var uc = UIChangeObservable()


    val login = BindingCommand<View>(BindingAction {
        if(username.get().isNullOrBlank()){
            uc.showUsernameError.set(true)
            return@BindingAction
        }
        uc.showUsernameError.set(false)
        if(password.get().isNullOrBlank()){
            uc.showPasswordError.set(true)
            return@BindingAction
        }
        uc.showPasswordError.set(false)
        Logger.i("" + password.get())
    })

}