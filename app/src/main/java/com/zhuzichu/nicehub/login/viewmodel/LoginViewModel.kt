package com.zhuzichu.nicehub.login.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.zhangwuji.helper.LoginInfoSp
import com.zhangwuji.im.utils.CommonUtil
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand
import com.zhuzichu.mvvm.bus.RxBus
import com.zhuzichu.mvvm.bus.event.SingleLiveEvent
import com.zhuzichu.mvvm.global.language.En
import com.zhuzichu.mvvm.global.language.LangConfig
import com.zhuzichu.mvvm.global.language.Zh
import com.zhuzichu.mvvm.utils.bindToLifecycle
import com.zhuzichu.mvvm.utils.exceptionTransformer
import com.zhuzichu.mvvm.utils.schedulersTransformer
import com.zhuzichu.mvvm.utils.toast
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.config.UrlConstant
import com.zhuzichu.nicehub.login.event.OnLoginEvent

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

    init {
        username.value = "18229858146"
        password.value = "7711451"
    }

    val en = BindingCommand<View>(BindingAction {
        LangConfig.initLang(En())
    })

    val zh = BindingCommand<View>(BindingAction {
        LangConfig.initLang(Zh())
    })

    val regist = BindingCommand<View>(BindingAction {
        toast("注册")
        startFragment(R.id.registFragment)
    })

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

        doUserLogin(username.value!!, CommonUtil.md5(password.value!!).toLowerCase())
    })

    /**
     *
     * @param username String
     * @param password String
     */
    @SuppressLint("CheckResult")
    private fun doUserLogin(username: String, password: String) {
        getIMService().userLogin(username, password)
                .compose(bindToLifecycle(getLifecycleProvider()))
                .compose(schedulersTransformer())
                .compose(exceptionTransformer())
                .doOnSubscribe {
                    showLoadingDialog()
                }
                .subscribe({
                    toast(it.data.toString())
                    val data = it.data
                    LoginInfoSp.instance().setLoginInfo(username, password, data.token, data.userinfo.peerId)
                    RxBus.default.post(OnLoginEvent(UrlConstant.appid, data.userinfo.peerId, username, data.token))
                    hideDialog()
                }, {
                    handleThrowable(it)
                    hideDialog()
                }, {
                })
    }
}