package com.zhuzichu.mvvm.base

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.orhanobut.logger.Logger
import com.trello.rxlifecycle3.components.support.RxFragment
import com.zhuzichu.mvvm.R
import com.zhuzichu.mvvm.view.layout.MultiStateView
import java.lang.reflect.ParameterizedType


/**
 * Created by wb.zhuzichu18 on 2019/1/15.
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : RxFragment(), IBaseFragment {
    lateinit var mBind: V
    lateinit var mViewModel: VM
    lateinit var contentView: View
    lateinit var multiStateView: MultiStateView
    private lateinit var mHandler: Handler
    private var mDialog: MaterialDialog? = null
    abstract fun setLayoutId(): Int
    abstract fun bindVariableId(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mHandler = Handler(Looper.getMainLooper())
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) mViewModel = ViewModelProviders.of(this).get(type.actualTypeArguments[1] as Class<VM>)
        lifecycle.addObserver(mViewModel)
        mViewModel.injectLifecycleProvider(this)

        if (::contentView.isInitialized) {
            val parent = contentView.parent
            if (parent != null) {
                (parent as ViewGroup).removeView(contentView)
            }
        } else {
            contentView = inflater.inflate(setLayoutId(), container, false)
            mViewModel.init()
        }

        mBind = DataBindingUtil.bind(contentView)!!
        mBind.setVariable(bindVariableId(), mViewModel)

        mHandler.postDelayed({ onEnterAnimationEnd(savedInstanceState) }, resources.getInteger(R.integer.fragment_anim_duration).toLong())

        multiStateView = inflater.inflate(R.layout.layout_multi_state, null) as MultiStateView
        multiStateView.addView(mBind.root)
        return multiStateView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registorUIChangeLiveDataCallBack()
        initVariable()
        initView()
        initViewObservable()
    }

    //注册ViewModel与View的契约UI回调事件
    private fun registorUIChangeLiveDataCallBack() {
        //跳入新Fragment页面
        mViewModel.getUC().getStartFragmentEvent().observe(this, Observer { params ->
            run {
                val id = params[BaseViewModel.ParameterField.ID] as Int
                val options = NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_left)
                        .setPopEnterAnim(R.anim.slide_in_left)
                        .setPopExitAnim(R.anim.slide_out_right)
                        .build()
                getBaseActivity().mNavController.navigate(id, null, options)
            }
        })
        //跳转到新Activity页面
        mViewModel.getUC().getStartActivityEvent().observe(this, Observer { params ->
            run {
                val clz = params[BaseViewModel.ParameterField.CLASS] as Class<*>
                val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle?
                val options = params[BaseViewModel.ParameterField.OPTIONS] as Bundle?
                val isPop = params[BaseViewModel.ParameterField.POP] as Boolean?
                val intent = Intent(activity, clz)
                bundle?.let {
                    intent.putExtras(it)
                }
                if (options != null) {
                    startActivity(intent, options)
                } else {
                    startActivity(intent)
                }
                if (isPop == true) {
                    activity?.finish()
                }
            }
        })
        //直接退出Activity页面
        mViewModel.getUC().getFinishEvent().observe(this, Observer { getBaseActivity().finish() })
        //有Fragment 退出fragment
        mViewModel.getUC().getOnBackPressedEvent().observe(this, Observer { getBaseActivity().onBackPressed() })
        //加载对话框显示
        mViewModel.getUC().getShowDialogEvent().observe(this, Observer { title -> showDialog(title) })
        //加载对话框消失
        mViewModel.getUC().getDismissDialogEvent().observe(this, Observer { dismissDialog() })

        mViewModel.getUC().getMultiStateEvent().observe(this, Observer { params ->
            run {
                multiStateView.viewState = params
            }
        })
    }

    fun dismissDialog() {
        mDialog?.dismiss()
    }

    fun showDialog(title: String) {
        if (mDialog != null) {
            mDialog?.message(text = title)
            mDialog?.show()
        } else {
            mDialog = MaterialDialog(getBaseActivity())
                    .message(text = title)
                    .cancelOnTouchOutside(false)
                    .customView(viewRes = R.layout.view_loading)
            mDialog?.show()
        }
    }

    fun getHandler(): Handler? {
        return mHandler
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mViewModel.isInitialized)
            lifecycle.removeObserver(mViewModel)
        if (::mBind.isInitialized)
            mBind.unbind()
    }

    /**
     * 转场动画结束回调
     */
    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        mViewModel.onEnterAnimationEnd()
    }

}