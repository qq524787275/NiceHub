package com.zhuzichu.nicehub.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand
import com.zhuzichu.mvvm.bus.event.SingleLiveEvent
import com.zhuzichu.mvvm.global.font.En
import com.zhuzichu.mvvm.global.font.FontConfig
import com.zhuzichu.mvvm.global.font.Zh
import com.zhuzichu.mvvm.http.ResponseThrowable
import com.zhuzichu.mvvm.http.model.AuthRequestModel
import com.zhuzichu.mvvm.utils.Convert
import com.zhuzichu.mvvm.utils.RxUtil.*
import okhttp3.Credentials

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginViewModel(application: Application) : BaseViewModel(application) {
    inner class UIChangeObservable {
        //密码开关观察
        var showUsernameError = SingleLiveEvent<Boolean>()
        var showPasswordError = SingleLiveEvent<Boolean>()
    }

    val username: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var uc = UIChangeObservable()

    val login = BindingCommand<View>(BindingAction {
        if (username.value.isNullOrBlank()) {
            uc.showUsernameError.value = true
            return@BindingAction
        }
        uc.showUsernameError.value = false
        if (password.value.isNullOrBlank()) {
            uc.showPasswordError.value = true
            return@BindingAction
        }
        uc.showPasswordError.value = false
        Logger.i("" + password.value)

        val token = Credentials.basic(username.value, password.value)
        doLogin(token)
    })

    val en = BindingCommand<View>(BindingAction {
        FontConfig.initFont(En())
    })

    val zh = BindingCommand<View>(BindingAction {
        FontConfig.initFont(Zh())
    })

    @SuppressLint("CheckResult")
    private fun doLogin(token: String) {
        val authRequestModel = AuthRequestModel.generate()
        getLoginService(token).authorizations(authRequestModel)
                .compose(bindToLifecycle(getLifecycleProvider()))
                .compose(schedulersTransformer())
                .compose(exceptionTransformer())
                .doOnSubscribe {
                    showDialog("登录中")
                }
                .subscribe({
                    Logger.i("成功了")
                    Logger.i(Convert.toJson(it))
                }, {
                    val e = it as ResponseThrowable
                    toast(e.msg)
                    hideDialog()
                }, {
                    Logger.i("结束了")
                    hideDialog()
                })
    }

}