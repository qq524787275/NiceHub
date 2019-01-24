package com.zhuzichu.mvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.annotation.NonNull
import com.zhuzichu.mvvm.App

object NetHelper {
    private const val TYPE_DISCONNECT = 0
    private const val TYPE_WIFI = 1
    private const val TYPE_MOBILE = 2

    private var mCurNetStatus: Int = 0

    /**
     * 检测当前网络状态
     */
    fun checkNet() {
        try {
            val connectivity = App.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 获取网络连接管理的对象
            val info = connectivity.activeNetworkInfo
            if (info != null && info.isAvailable) {
                // 判断当前网络是否已经连接
                if (info.state == NetworkInfo.State.CONNECTED) {
                    if (info.type == ConnectivityManager.TYPE_WIFI)
                        mCurNetStatus = TYPE_WIFI
                    if (info.type == ConnectivityManager.TYPE_MOBILE)
                        mCurNetStatus = TYPE_MOBILE
                }
            } else {
                mCurNetStatus = TYPE_DISCONNECT
            }
        } catch (e: Exception) {
            Log.v("error", e.toString())
            e.printStackTrace()
            mCurNetStatus = TYPE_DISCONNECT
        }

    }

    /**
     * 网络是否可用
     * @return
     */
    @NonNull
    fun getNetEnabled(): Boolean {
        return mCurNetStatus == TYPE_MOBILE || mCurNetStatus == TYPE_WIFI
    }

    /**
     * 是否处于移动网络状态
     * @return
     */
    @NonNull
    fun isMobileStatus(): Boolean {
        return mCurNetStatus == TYPE_MOBILE
    }

    /**
     * 获取当前网络状态
     * @return
     */
    fun getNetStatus(): Int {
        return mCurNetStatus
    }
}