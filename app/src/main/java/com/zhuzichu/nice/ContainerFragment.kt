package com.zhuzichu.nice


import android.os.Bundle
import android.view.View
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nice.databinding.FragmentContainerBinding

class ContainerFragment : BaseFragment<FragmentContainerBinding, ContainerViewModel>() {
    override fun bindVariableId(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_container
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
