package com.zhuzichu.nicehub.main.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.mvvm.bus.Event
import com.zhuzichu.mvvm.bus.RxBus
import com.zhuzichu.mvvm.global.language.LangConfig
import com.zhuzichu.mvvm.global.theme.ThemeConfig
import com.zhuzichu.mvvm.utils.bindToLifecycle
import com.zhuzichu.mvvm.utils.createBitmapFromView
import com.zhuzichu.mvvm.view.reveal.animation.ViewAnimationUtils
import com.zhuzichu.mvvm.view.viewpage.NiceViewPagerAdapter
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentMainBinding
import com.zhuzichu.nicehub.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
import me.majiajie.pagerbottomtabstrip.item.NormalItemView


class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        mBind.theme = ThemeConfig
    }

    override fun initView() {
        initBottomNavigation()
    }

    lateinit var fragments: ArrayList<Fragment>

    private fun initBottomNavigation() {
        val navigationController = bottom.custom()
                .addItem(newItem(R.drawable.ic_menu_news_gray, R.drawable.ic_menu_news, LangConfig.news.get().toString()))
                .addItem(newItem(R.drawable.ic_menu_person_gray, R.drawable.ic_menu_person, LangConfig.profile.get().toString()))
                .build()

        fragments = ArrayList(navigationController.itemCount)
        fragments.add(NewsFragment())
        fragments.add(ProfileFragment())

        content.offscreenPageLimit = navigationController.itemCount
        content.adapter = NiceViewPagerAdapter(getBaseActivity().supportFragmentManager, fragments)

        navigationController.setupWithViewPager(content)
    }

    //创建一个Item
    private fun newItem(drawable: Int, checkedDrawable: Int, text: String): BaseTabItem {
        val normalItemView = NormalItemView(context)
        normalItemView.initialize(drawable, checkedDrawable, text)
        normalItemView.setTextDefaultColor(Color.GRAY)
        normalItemView.setTextCheckedColor(-0xff6978)
        return normalItemView
    }

    @SuppressLint("CheckResult")
    override fun initViewObservable() {
        RxBus.default.toObservable(Event.ThemeChangeEvent::class.java)
                .compose(bindToLifecycle(mViewModel.getLifecycleProvider()))
                .subscribe {
                    val centerX = (layout_content.left + layout_content.right) / 2
                    val centerY = (layout_content.top + layout_content.bottom) / 2
                    val finalRadius = Math.hypot(centerX.toDouble(), centerY.toDouble()).toFloat()
                    val mCircularReveal = ViewAnimationUtils.createCircularReveal(layout_content, centerX, centerY, 0f, finalRadius)
                    mCircularReveal.interpolator = AccelerateInterpolator()
                    val drawable = BitmapDrawable(createBitmapFromView(layout_content))
                    layout_overlay.setBackgroundDrawable(drawable)
                    mCircularReveal.setDuration(500).start()
                }
    }

    companion object {
        private val PAGE_IDS = arrayOf(R.id.newsFragment, R.id.profileFragment)
    }
}