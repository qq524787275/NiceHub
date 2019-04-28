package com.zhuzichu.nicehub.login.activity

import android.os.Bundle
import android.text.TextUtils
import com.zhangwuji.im.DB.sp.SystemConfigSp
import com.zhangwuji.im.imcore.service.IMService
import com.zhangwuji.im.imcore.service.IMServiceConnector
import com.zhuzichu.mvvm.base.BaseActivity
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.config.UrlConstant

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginActivity : BaseActivity() {

    lateinit var imService: IMService

    val imServiceConnector = object : IMServiceConnector() {
        override fun onServiceDisconnected() {

        }

        override fun onIMServiceConnected() {
            this@LoginActivity.imService = this.imService

        }
    }


    override fun setNavGraph(): Int = R.navigation.navigation_login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemConfigSp.instance().init(applicationContext)

        if (TextUtils.isEmpty(SystemConfigSp.instance().getStrConfig(SystemConfigSp.SysCfgDimension.LOGINSERVER))) {
            SystemConfigSp.instance().setStrConfig(SystemConfigSp.SysCfgDimension.LOGINSERVER, UrlConstant.ACCESS_MSG_ADDRESS)
        }
        if (TextUtils.isEmpty(SystemConfigSp.instance().getStrConfig(SystemConfigSp.SysCfgDimension.APPID))) {
            SystemConfigSp.instance().setStrConfig(SystemConfigSp.SysCfgDimension.APPID, UrlConstant.appid)
        }

        imServiceConnector.connect(this@LoginActivity)
    }

}