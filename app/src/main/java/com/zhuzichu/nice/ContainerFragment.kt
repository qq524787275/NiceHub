package com.zhuzichu.nice


import android.os.Bundle
import android.view.View
import com.zhuzichu.mvvm.BaseFragment
import com.zhuzichu.nice.databinding.FragmentContainerBinding
import kotlinx.android.synthetic.main.fragment_container.*

class ContainerFragment : BaseFragment<FragmentContainerBinding>() {
    override fun setLayoutId(): Int {
        return R.layout.fragment_container
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button.setOnClickListener { getBaseActivity().mNavController.navigate(R.id.action_fragment_container_to_fragment_detail) }
    }
}
