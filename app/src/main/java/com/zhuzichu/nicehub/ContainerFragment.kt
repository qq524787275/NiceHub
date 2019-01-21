package com.zhuzichu.nicehub


import android.os.Bundle
import android.view.View
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nicehub.databinding.FragmentContainerBinding

class ContainerFragment : BaseFragment<FragmentContainerBinding, ContainerViewModel>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
