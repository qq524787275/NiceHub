package com.zhuzichu.nicehub.main.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.orhanobut.logger.Logger
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.data.model.Event
import com.zhuzichu.mvvm.utils.bindToLifecycle
import com.zhuzichu.mvvm.utils.exceptionTransformer
import com.zhuzichu.mvvm.utils.schedulersTransformer
import com.zhuzichu.mvvm.utils.toJson
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import me.tatarka.bindingcollectionadapter2.ItemBinding


class NewsViewModel(application: Application) : BaseViewModel(application) {
    val items: ObservableList<Event> = ObservableArrayList<Event>()
    val itemBinding: ItemBinding<Event> = ItemBinding.of(BR.item, R.layout.item_news)

    override fun init() {
//        loadNewsEvent(account = UserConfig.user.get()?.login.toString(), page = 0)
    }

    @SuppressLint("CheckResult")
    fun loadNewsEvent(account: String, page: Int) {
        getUserService().getNewsEvent(user = account, page = page, forceNetWork = false)
                .compose(bindToLifecycle(getLifecycleProvider()))
                .compose(schedulersTransformer())
                .compose(exceptionTransformer())
                .doOnSubscribe {
                    showLoading()
                }
                .subscribe {
                    showContent()
                    Logger.i(toJson(it))
                    items.addAll(it)
                }
    }
}