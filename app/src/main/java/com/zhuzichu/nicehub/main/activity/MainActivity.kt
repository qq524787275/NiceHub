package com.zhuzichu.nicehub.main.activity


import com.zhuzichu.mvvm.base.BaseActivity
import com.zhuzichu.nicehub.R

class MainActivity : BaseActivity() {
    override fun setNavGraph(): Int = R.navigation.navigation_main

    override fun onSupportNavigateUp(): Boolean {
        return true
    }
}
