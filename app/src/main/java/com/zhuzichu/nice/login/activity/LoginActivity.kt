package com.zhuzichu.nice.login.activity

import com.zhuzichu.mvvm.base.BaseActivity
import com.zhuzichu.nice.R

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginActivity : BaseActivity() {
    override fun setNavGraph(): Int {
        return R.navigation.login_navigation
    }
}