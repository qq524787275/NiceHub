package com.zhuzichu.nicehub.main.fragment

import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentMainBinding
import com.zhuzichu.nicehub.main.viewmodel.MainViewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel
}