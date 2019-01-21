package com.zhuzichu.nicehub


import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nicehub.databinding.FragmentDetailBinding

/**
 * Created by wb.zhuzichu18 on 2019/1/15.
 */
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun bindVariableId(): Int {
        return BR.viewModel
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_detail
    }

}