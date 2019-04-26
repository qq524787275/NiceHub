package com.zhuzichu.nicehub.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.logger.Logger
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.mvvm.global.language.LangConfig
import com.zhuzichu.mvvm.global.theme.ThemeConfig
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentNewsBinding
import com.zhuzichu.nicehub.main.viewmodel.NewsViewModel

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {
    override fun setLayoutId(): Int = R.layout.fragment_news

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        mBind.lang = LangConfig
        mBind.theme = ThemeConfig
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.i("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.i("onDestroyView")
    }

}