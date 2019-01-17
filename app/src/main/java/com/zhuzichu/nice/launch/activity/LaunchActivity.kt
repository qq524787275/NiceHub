package com.zhuzichu.nice.launch.activity

import com.zhuzichu.mvvm.base.BaseActivity
import com.zhuzichu.nice.R

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LaunchActivity : BaseActivity() {
    override fun setNavGraph(): Int {
        return R.navigation.launch_navigation
    }
}