package com.zhuzichu.nicehub.main.fragment

import androidx.navigation.Navigation
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.mvvm.global.language.LangConfig
import com.zhuzichu.mvvm.global.theme.ThemeConfig
import com.zhuzichu.mvvm.utils.setupWithNavController
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentMainBinding
import com.zhuzichu.nicehub.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        mBind.theme=ThemeConfig
    }

    override fun initView() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navigationController = bottom.material()
                .addItem(R.drawable.ic_menu_news, LangConfig.news.get().toString())
                .addItem(R.drawable.ic_menu_person, LangConfig.profile.get().toString())
                .build()
        setupWithNavController(PAGE_IDS.toIntArray(), navigationController, Navigation.findNavController(getBaseActivity(), R.id.content))
    }


    companion object {
        private val PAGE_IDS = arrayOf(R.id.newsFragment, R.id.profileFragment)
    }
}