package com.zhuzichu.nice.main.activity


import com.zhuzichu.mvvm.base.BaseActivity
import com.zhuzichu.nice.R

class MainActivity : BaseActivity() {
    override fun setNavGraph(): Int {
        return R.navigation.main_navigation
    }
}
