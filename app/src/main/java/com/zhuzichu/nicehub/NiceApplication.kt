package com.zhuzichu.nicehub

import android.content.Context
import androidx.multidex.MultiDex
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.zhangwuji.im.IMClient
import com.zhuzichu.mvvm.App
import com.zhuzichu.mvvm.crash.CaocConfig
import com.zhuzichu.nicehub.main.activity.MainActivity


/**
 * Created by wb.zhuzichu18 on 2019/1/16.
 */
class NiceApplication : App() {
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())

        //初始化全局异常崩溃
        initCrash()

        IMClient.init(this)
//        startIMService()
    }

//    private fun startIMService() {
//        Logger.i("start IMService")
//        val intent = Intent()
//        intent.setClass(this, IMService::class.java!!)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(intent)
//        } else {
//            startService(intent)
//        }
//    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
//                .errorDrawable(R.mipmap.ic_launcher)
                .restartActivity(MainActivity::class.java) //重新启动后的activity
                .apply()
    }
}