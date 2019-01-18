package com.zhuzichu.nice.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.orhanobut.logger.Logger
import com.zhuzichu.mvvm.base.IBaseView
import com.zhuzichu.mvvm.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import com.zhuzichu.nice.R

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

    override fun onStart() {
        super.onStart()
        subscribe = Observable.interval(period.toLong(), TimeUnit.SECONDS)
                .compose(RxUtils.schedulersTransformer())
                .subscribe { l ->
                    Logger.i(l.toString())
                    visibility = View.VISIBLE
                    text = s
                    if (l == time.toLong()) {
                        if (::listener.isInitialized) {
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