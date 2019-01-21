package com.zhuzichu.nicehub


import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nicehub.databinding.FragmentDetailBinding

/**
 * Created by wb.zhuzichu18 on 2019/1/15.
 */
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_detail

}