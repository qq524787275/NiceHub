package com.zhuzichu.nice.launch.viewmodel

import android.app.Application
import androidx.core.app.ActivityOptionsCompat
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.nice.login.activity.LoginActivity
import com.zhuzichu.nice.R

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LaunchViewModel(application: Application) : BaseViewModel(application) {
    override fun onCreate() {
        super.onCreate()
        //        var options = ActivityOptionsCompat.makeCustomAnimation(getApplication(), R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim).toBundle()
        startActivity(LoginActivity::class.java, isPop = true)
    }
}