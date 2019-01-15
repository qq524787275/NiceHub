package com.zhuzichu.nice


import com.zhuzichu.mvvm.BaseActivity

class MainActivity : BaseActivity() {
    override fun setNavGraph(): Int {
        return R.navigation.mobile_navigation
    }
}
