package com.zhuzichu.nice.launch.fragment

import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nice.BR
import com.zhuzichu.nice.databinding.FragmentLaunchBinding
import com.zhuzichu.nice.launch.viewmodel.LaunchViewModel
import com.zhuzichu.nice.R

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LaunchFragment : BaseFragment<FragmentLaunchBinding, LaunchViewModel>() {
    override fun setLayoutId(): Int {
        return R.layout.fragment_launch
    }

    override fun bindVariableId(): Int {
        return BR.viewModel
    }
}