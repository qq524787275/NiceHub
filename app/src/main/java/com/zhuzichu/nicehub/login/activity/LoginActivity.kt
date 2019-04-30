package com.zhuzichu.nicehub.login.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.core.app.ActivityOptionsCompat
import com.orhanobut.logger.Logger
import com.zhangwuji.helper.LoginInfoSp
import com.zhangwuji.im.DB.sp.LoginSp
import com.zhangwuji.im.DB.sp.SystemConfigSp
import com.zhangwuji.im.imcore.event.LoginEvent
import com.zhangwuji.im.imcore.event.SocketEvent
import com.zhangwuji.im.imcore.service.IMService
import com.zhangwuji.im.imcore.service.IMServiceConnector
import com.zhuzichu.mvvm.base.BaseActivity
import com.zhuzichu.mvvm.bus.RxBus
import com.zhuzichu.mvvm.utils.toast
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.config.UrlConstant
import com.zhuzichu.nicehub.login.event.OnLoginEvent
import com.zhuzichu.nicehub.main.activity.MainActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginActivity : BaseActivity() {

    lateinit var imService: IMService
    private val uiHandler = Handler()
    lateinit var loginInfoIdentity: LoginInfoSp.LoginInfoSpIdentity
    private var loginSuccess = false
    private var firstLogin = true
    private var autoLogin = true

    private val imServiceConnector = object : IMServiceConnector() {
        override fun onServiceDisconnected() {

        }

        override fun onIMServiceConnected() {
            Logger.i("onIMServiceConnected")
            this@LoginActivity.imService = this.imService
            try {
                Logger.i("1")
                do {
                    Logger.i("2")
                    if (imService == null) {
                        //后台服务启动链接失败
                        Logger.i("后台服务启动链接失败")
                        break
                    }
                    Logger.i("3")
                    val loginManager = imService.loginManager
                    val loginSp = imService.loginSp
                    if (loginManager == null || loginSp == null || loginInfoIdentity == null) {
                        // 无法获取登陆控制器
                        Logger.i("无法获取登陆控制器")
                        break
                    }
                    Logger.i("4")
                    if (TextUtils.isEmpty(this@LoginActivity.loginInfoIdentity!!.loginName)) {
                        break
                    }
                    toast(loginInfoIdentity.loginName)
                    if (!autoLogin) {
                        break
                    }
                    Logger.i("5")
                    //先判断业务层有没有用户信息
                    if (TextUtils.isEmpty(loginInfoIdentity.token) || loginInfoIdentity.loginId <= 0) {
                        break
                    }
                    Logger.i("6")
                    //再判断im有没有存用户信息。
                    val loginIdentity = loginSp.loginIdentity
                    Logger.i(loginIdentity.token)
                    Logger.i(loginIdentity.loginId.toString())
                    if (loginIdentity == null || TextUtils.isEmpty(loginIdentity.token) || loginIdentity.loginId <= 0) {
                        Logger.i("再判断im有没有存用户信息")
                        break
                    }
                    Logger.i("7")
                    firstLogin = false
                    //开始自动登录
                    handleGotLoginIdentity(loginIdentity)
                    return
                } while (false)
                Logger.i("while (false)")
                // 异常分支都会执行这个
                handleNoLoginIdentity()
            } catch (e: Exception) {
                // 任何未知的异常
                Logger.i("任何未知的异常")
                handleNoLoginIdentity()
            }

        }
    }

    @SuppressLint("CheckResult")
    private fun subscribeOnLogin() {
        RxBus.default.toObservable(OnLoginEvent::class.java)
                .compose(bindToLifecycle())
                .subscribe {
                    toast("執行了")
                    imService.loginManager.login(it.appid, it.peerId, it.username, it.token)
                }
    }

    private fun handleNoLoginIdentity() {
        toast("要登录")
    }

    private fun handleGotLoginIdentity(loginIdentity: LoginSp.SpLoginIdentity?) {
        if (imService.loginManager == null) {
            toast("登录失败")
        }
        imService.loginManager.login(UrlConstant.appid, loginIdentity)
    }


    override fun setNavGraph(): Int = R.navigation.navigation_login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOnLogin()
        EventBus.getDefault().register(this)
        val loginInfoSp = LoginInfoSp.instance()

        loginInfoSp.init(this)
        loginInfoIdentity = loginInfoSp.loginInfoIdentity

        SystemConfigSp.instance().init(applicationContext)

        if (TextUtils.isEmpty(SystemConfigSp.instance().getStrConfig(SystemConfigSp.SysCfgDimension.LOGINSERVER))) {
            SystemConfigSp.instance().setStrConfig(SystemConfigSp.SysCfgDimension.LOGINSERVER, UrlConstant.ACCESS_MSG_ADDRESS)
        }
        if (TextUtils.isEmpty(SystemConfigSp.instance().getStrConfig(SystemConfigSp.SysCfgDimension.APPID))) {
            SystemConfigSp.instance().setStrConfig(SystemConfigSp.SysCfgDimension.APPID, UrlConstant.appid)
        }

        imServiceConnector.connect(this@LoginActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        imServiceConnector.disconnect(this@LoginActivity)
    }

    /**
     * ----------------------------event 事件驱动----------------------------
     */

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.LOCAL_LOGIN_SUCCESS, LoginEvent.LOGIN_OK -> onLoginSuccess()
            LoginEvent.LOGIN_AUTH_FAILED, LoginEvent.LOGIN_INNER_FAILED -> if (!loginSuccess)
                onLoginFailure(event)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserEvent(event: SocketEvent) {
        when (event) {
            SocketEvent.CONNECT_MSG_SERVER_FAILED, SocketEvent.REQ_MSG_SERVER_ADDRS_FAILED -> if (!loginSuccess)
                onSocketFailure(event)
        }
    }

    private fun onLoginSuccess() {
        loginSuccess = true

        imService.contactManager.reqGetAllUsers(if (firstLogin) 1 else 0)

        val options = ActivityOptionsCompat.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out).toBundle()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent, options)
        finish()
    }

    private fun onLoginFailure(event: LoginEvent) {
        Logger.i("登录失败");
    }

    private fun onSocketFailure(event: SocketEvent) {
        Logger.i("登录socket失败")
    }

}