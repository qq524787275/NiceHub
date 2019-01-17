package com.zhuzichu.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.zhuzichu.mvvm.R
import kotlinx.android.synthetic.main.activity_base.*

/**
 * Created by wb.zhuzichu18 on 2019/1/15.
 */

abstract class BaseActivity : AppCompatActivity() {
    lateinit var mNavController: NavController
    abstract fun setNavGraph(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
    }

    private fun initContainer(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_base)
        mNavController = container.findNavController()
        if (savedInstanceState == null) {
            mNavController.setGraph(setNavGraph(), savedInstanceState)
        }
    }
}