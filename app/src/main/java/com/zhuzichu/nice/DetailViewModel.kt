package com.zhuzichu.nice

import android.app.Application
import com.orhanobut.logger.Logger
import com.zhuzichu.mvvm.BaseViewModel

/**
 * Created by wb.zhuzichu18 on 2019/1/16.
 */
class DetailViewModel(application: Application) : BaseViewModel(application) {

    override fun onCreate() {
        super.onCreate()
        Logger.i("创建了----------------")
    }

    override fun onResume() {
        super.onResume()
        showLoading()
    }
}