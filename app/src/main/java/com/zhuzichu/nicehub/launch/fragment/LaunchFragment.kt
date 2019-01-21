package com.zhuzichu.nicehub.launch.fragment

import android.os.Bundle
import android.view.View
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentLaunchBinding
import com.zhuzichu.nicehub.launch.viewmodel.LaunchViewModel
import kotlinx.android.synthetic.main.fragment_launch.*

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LaunchFragment : BaseFragment<FragmentLaunchBinding, LaunchViewModel>() {
    override fun setLayoutId(): Int = R.layout.fragment_launch

    override fun bindVariableId(): Int = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(jump)
        jump.setTimeEndListener { mViewModel.jump.execute() }
    }

}