package com.zhuzichu.nicehub.login.fragment

import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentRegistBinding
import com.zhuzichu.nicehub.login.viewmodel.RegistViewModel

class RegistFragment : BaseFragment<FragmentRegistBinding, RegistViewModel>() {
    override fun setLayoutId(): Int = R.layout.fragment_regist
    override fun bindVariableId(): Int = BR.viewModel
}