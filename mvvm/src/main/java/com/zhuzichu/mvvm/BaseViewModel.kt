package com.zhuzichu.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.trello.rxlifecycle3.LifecycleProvider
import com.zhuzichu.mvvm.bus.event.SingleLiveEvent


/**
 * Created by wb.zhuzichu18 on 2019/1/16.
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), IBaseViewModel {
    private var uc: UIChangeLiveData? = null
    private lateinit var lifecycle: LifecycleProvider<*>

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    fun injectLifecycleProvider(lifecycle: LifecycleProvider<*>) {
        this.lifecycle = lifecycle
    }

    fun getLifecycleProvider(): LifecycleProvider<*>? {
        return lifecycle
    }

    fun getUC(): UIChangeLiveData {
        if (uc == null) {
            uc = UIChangeLiveData()
        }
        return uc as UIChangeLiveData
    }

    fun showLoading() {
        showDialog("请稍后...")
    }

    fun showDialog(title: String) {
        uc?.showDialogEvent?.postValue(title)
    }

    inner class UIChangeLiveData : SingleLiveEvent<Any>() {
        internal val showDialogEvent: SingleLiveEvent<String> = SingleLiveEvent()
        private val dismissDialogEvent: SingleLiveEvent<Void> = SingleLiveEvent()
        private val startActivityEvent: SingleLiveEvent<Map<String, Any>> = SingleLiveEvent()
        private val startContainerActivityEvent: SingleLiveEvent<Map<String, Any>> = SingleLiveEvent()
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

        fun getStartContainerActivityEvent(): SingleLiveEvent<Map<String, Any>> {
            return startContainerActivityEvent
        }

        fun getFinishEvent(): SingleLiveEvent<Void> {
            return finishEvent
        }

        fun getOnBackPressedEvent(): SingleLiveEvent<Void> {
            return onBackPressedEvent
        }
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

}