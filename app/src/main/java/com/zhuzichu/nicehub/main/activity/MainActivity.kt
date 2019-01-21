package com.zhuzichu.nicehub.main.activity


import com.zhuzichu.mvvm.base.BaseActivity
import com.zhuzichu.nicehub.R

class MainActivity : BaseActivity() {
    override fun setNavGraph(): Int {
        return R.navigation.main_navigation
    }
}
