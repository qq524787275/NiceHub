package com.zhuzichu.nicehub.launch.viewmodel

import android.app.Application
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.ObservableField
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.login.activity.LoginActivity

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LaunchViewModel(application: Application) : BaseViewModel(application) {

    var urls: ObservableField<Array<String>> = ObservableField(TRANSITION_URLS)

    val jump: BindingCommand<View> = BindingCommand(BindingAction {
        var options = ActivityOptionsCompat.makeCustomAnimation(getApplication(), R.anim.screen_zoom_in, R.anim.screen_zoom_out).toBundle()
        startActivity(LoginActivity::class.java, isPop = true, options = options)
    })

    companion object {
        private const val TRANSITION_URL_01: String = "http://sjbz.fd.zol-img.com.cn/t_s640x960c/g5/M00/00/00/ChMkJ1fJTnOIMlD6AATJiv4hjD4AAU9MgIwLTgABMmi128.jpg"
        private const val TRANSITION_URL_02: String = "http://sjbz.fd.zol-img.com.cn/t_s640x960c/g5/M00/00/00/ChMkJ1fJTnKIPuPrAASSLwswZsoAAU9MgImyLsABJJH496.jpg"
        val TRANSITION_URLS = arrayOf(TRANSITION_URL_01, TRANSITION_URL_02)
    }
}