package com.zhuzichu.nice

import android.app.Application
import com.zhuzichu.mvvm.base.BaseViewModel

/**
 * Created by wb.zhuzichu18 on 2019/1/16.
 */
class DetailViewModel(application: Application) : BaseViewModel(application) {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onEnterAnimationEnd() {
        super.onEnterAnimationEnd()
        showLoading()
    }

    override fun onResume() {
        super.onResume()
    }
}