package com.zhuzichu.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by wb.zhuzichu18 on 2019/1/15.
 */
abstract class BaseFragment<V : ViewDataBinding> : Fragment() {
    var mBind: V? = null
    abstract fun setLayoutId(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(setLayoutId(), container, false)
        mBind = root?.let { DataBindingUtil.bind(it) }
        return mBind?.root
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }
}