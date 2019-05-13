package com.zhuzichu.nicehub.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.zhuzichu.mvvm.base.IBaseView
import com.zhuzichu.mvvm.utils.schedulersTransformer
import com.zhuzichu.nicehub.R
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Created by wb.zhuzichu18 on 2019/1/18.
 */
class JumpView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), IBaseView {

    private lateinit var subscribe: Disposable
    private lateinit var listener: () -> Unit
    private var period: Int
    private var time: Int
    private val s: String = "跳过(%d)"

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.JumpView, defStyleAttr, 0)
        try {
            period = attributes.getInt(R.styleable.JumpView_period, 1)
            time = attributes.getInt(R.styleable.JumpView_time, 3)
        } finally {
            attributes.recycle()
        }
    }

    override fun onCreate() {
        super.onCreate()
        subscribe = Observable.interval(
                period.toLong(), TimeUnit.SECONDS)
                .compose(schedulersTransformer())
                .doOnSubscribe {
                    visibility = View.VISIBLE
                    text = String.format(s, time)
                }
                .subscribe {
                    text = String.format(s, time.toLong().minus(it as Long) - 1)
                    if (it == time.toLong() - 1) {
                        if (::listener.isInitialized && !isVisible) {
                            listener()
                        }
                    }
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe.dispose()
    }

    fun setTimeEndListener(listener: () -> Unit) {
        this@JumpView.listener = listener
    }
}