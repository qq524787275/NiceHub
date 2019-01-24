package com.zhuzichu.mvvm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.zhuzichu.mvvm.bus.Event
import com.zhuzichu.mvvm.bus.RxBus
import com.zhuzichu.mvvm.utils.NetHelper

class NetBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        val action = intent?.action
        if (ConnectivityManager.CONNECTIVITY_ACTION == action) {
            val preNetStatus = NetHelper.getNetStatus()
            NetHelper.checkNet()
            val curNetStatus = NetHelper.getNetStatus()
            RxBus.default.post(Event.NetChangedEvent(preNetStatus, curNetStatus))
        }
    }
}