package com.zhuzichu.nicehub.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand
import com.zhuzichu.mvvm.bus.event.SingleLiveEvent
import com.zhuzichu.mvvm.global.language.En
import com.zhuzichu.mvvm.global.language.LangConfig
import com.zhuzichu.mvvm.global.language.Zh
import com.zhuzichu.mvvm.global.user.UserConfig
import com.zhuzichu.mvvm.http.model.AuthRequestModel
import com.zhuzichu.mvvm.utils.bindToLifecycle
import com.zhuzichu.mvvm.utils.exceptionTransformer
import com.zhuzichu.mvvm.utils.schedulersTransformer
import com.zhuzichu.nicehub.main.activity.MainActivity
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
        val token = Credentials.basic(username.value, password.value)
        doLogin(token)
    })

    val en = BindingCommand<View>(BindingAction {
        LangConfig.initLang(En())
    })

    val zh = BindingCommand<View>(BindingAction {
        LangConfig.initLang(Zh())
    })


    @SuppressLint("CheckResult")
    private fun doLogin(token: String) {
        val authRequestModel = AuthRequestModel.generate()
        getLoginService(token).authorizations(authRequestModel)
                .compose(bindToLifecycle(getLifecycleProvider()))
                .compose(schedulersTransformer())
                .compose(exceptionTransformer())
                .doOnSubscribe {
                    showLoading()
                }
                .subscribe({
                    UserConfig.basecToken.set(it)
                    doGetUser(it.token)
                }, {
                    handleThrowable(it)
                    hideDialog()
                }, {
                })
    }

    @SuppressLint("CheckResult")
    private fun doGetUser(token: String?) {
        getUserService(token).getPersonInfo(false)
                .compose(bindToLifecycle(getLifecycleProvider()))
                .compose(schedulersTransformer())
                .compose(exceptionTransformer())
                .subscribe({
                    UserConfig.user.set(it)
                    var options = ActivityOptionsCompat.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out).toBundle()
                    startActivity(clz = MainActivity::class.java, isPop = true, options = options)
                }, {
                    hideDialog()
                }, {
                    hideDialog()
                })
    }
}