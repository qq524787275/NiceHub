package com.zhuzichu.nicehub.main.fragment

import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.mvvm.global.font.FontConfig
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentNewsBinding
import com.zhuzichu.nicehub.main.viewmodel.NewsViewModel

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {
    override fun setLayoutId(): Int = R.layout.fragment_news

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        mBind.font= FontConfig
    }
}