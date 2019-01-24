package com.zhuzichu.mvvm.base

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.trello.rxlifecycle3.LifecycleProvider
import com.zhuzichu.mvvm.bus.event.SingleLiveEvent
import com.zhuzichu.mvvm.global.font.FontConfig
import com.zhuzichu.mvvm.http.ResponseThrowable
import com.zhuzichu.mvvm.http.service.IService
import com.zhuzichu.mvvm.utils.toast
import java.util.*

/**
 * Created by wb.zhuzichu18 on 2019/1/16.
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), IBaseViewModel, IService {
    val context: Context = application.applicationContext
    private val uc: UIChangeLiveData = UIChangeLiveData()
    private lateinit var lifecycle: LifecycleProvider<*>
    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    fun injectLifecycleProvider(lifecycle: LifecycleProvider<*>) {
        this.lifecycle = lifecycle
    }

    fun getLifecycleProvider(): LifecycleProvider<*> {
        return lifecycle
    }

    fun getUC(): UIChangeLiveData {
        return uc
    }

    fun showLoading() {
        showDialog(""" ${FontConfig.loading.get()}... """)
    }

    fun showDialog(title: String) {
        uc.getShowDialogEvent().postValue(title)
    }

    fun hideDialog() {
        uc.getDismissDialogEvent().call()
    }


    fun handleThrowable(throwable: Throwable) {
        when (throwable) {
            is ResponseThrowable -> toast(throwable.msg)
        }
    }

    fun startFragment(action: Int, bundle: Bundle? = null) {
        val params = HashMap<String, Any>()
        params[ParameterField.ACTION] = action
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uc.getStartFragmentEvent().postValue(params)
    }

    fun startActivity(clz: Class<*>, bundle: Bundle? = null, isPop: Boolean? = false, options: Bundle? = null) {
        val params = HashMap<String, Any>()
        params[ParameterField.CLASS] = clz
        bundle?.let { params[ParameterField.BUNDLE] = it }
        options?.let { params[ParameterField.OPTIONS] = it }
        isPop?.let { params[ParameterField.POP] = it }
        uc.getStartActivityEvent().postValue(params)
    }

    inner class UIChangeLiveData : SingleLiveEvent<Any>() {
        private val showDialogEvent: SingleLiveEvent<String> = SingleLiveEvent()
        private val dismissDialogEvent: SingleLiveEvent<Void> = SingleLiveEvent()
        private val startActivityEvent: SingleLiveEvent<Map<String, Any>> = SingleLiveEvent()
        private val startFragmentEvent: SingleLiveEvent<Map<String, Any>> = SingleLiveEvent()
        private val finishEvent: SingleLiveEvent<Void> = SingleLiveEvent()
        private val onBackPressedEvent: SingleLiveEvent<Void> = SingleLiveEvent()

        fun getShowDialogEvent(): SingleLiveEvent<String> {
            return showDialogEvent
        }

        fun getDismissDialogEvent(): SingleLiveEvent<Void> {
            return dismissDialogEvent
        }

        fun getStartActivityEvent(): SingleLiveEvent<Map<String, Any>> {
            return startActivityEvent
        }

        fun getStartFragmentEvent(): SingleLiveEvent<Map<String, Any>> {
            return startFragmentEvent
        }

        fun getFinishEvent(): SingleLiveEvent<Void> {
            return finishEvent
        }

        fun getOnBackPressedEvent(): SingleLiveEvent<Void> {
            return onBackPressedEvent
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in Any>) {
            super.observe(owner, observer)
        }
    }

    object ParameterField {
        var ACTION = "ACTION"
        var CLASS = "CLASS"
        var BUNDLE = "BUNDLE"
        var POP = "POP"
        var OPTIONS = "OPTIONS"
        var CANONICAL_NAME = "CANONICAL_NAME"
    }

    override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {
    }

    override fun onCreate() {
    }

    override fun onDestroy() {
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onEnterAnimationEnd() {
    }
}