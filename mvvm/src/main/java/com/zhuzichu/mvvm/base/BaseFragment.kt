package com.zhuzichu.mvvm.base

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.trello.rxlifecycle3.components.support.RxFragment
import androidx.lifecycle.ViewModelProviders
import com.orhanobut.logger.Logger
import java.lang.reflect.ParameterizedType
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.zhuzichu.mvvm.R


/**
 * Created by wb.zhuzichu18 on 2019/1/15.
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : RxFragment(), IBaseFragment {
    private lateinit var mBind: V
    private lateinit var mViewModel: VM
    private lateinit var mHandler: Handler
    private var mDialog: MaterialDialog? = null
    abstract fun setLayoutId(): Int
    abstract fun bindVariableId(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.i(this.javaClass.simpleName + "---创建了")
        val root = inflater.inflate(setLayoutId(), container, false)
        mHandler = Handler(Looper.getMainLooper())
        mBind = DataBindingUtil.bind(root)!!
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) mViewModel = ViewModelProviders.of(this).get(type.actualTypeArguments[1] as Class<VM>)
        mBind.setVariable(bindVariableId(), mViewModel)
        lifecycle.addObserver(mViewModel)
        mViewModel.injectLifecycleProvider(this)
        mHandler.postDelayed({ onEnterAnimationEnd(savedInstanceState) }, resources.getInteger(R.integer.fragment_anim_duration).toLong())
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registorUIChangeLiveDataCallBack()
    }

    //注册ViewModel与View的契约UI回调事件
    private fun registorUIChangeLiveDataCallBack() {
        //加载对话框显示
        mViewModel.getUC().getShowDialogEvent().observe(this, Observer { title -> showDialog(title) })
        //加载对话框消失
        mViewModel.getUC().getDismissDialogEvent().observe(this, Observer { dismissDialog() })
        //跳入新Fragment页面
        mViewModel.getUC().getStartFragmentEvent().observe(this, Observer { params ->
            run {
                val action = params[BaseViewModel.ParameterField.ACTION] as Int
                val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle?
                getBaseActivity().mNavController.navigate(action, bundle)
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
    }

    fun getHandler(): Handler? {
        return mHandler
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    private fun dismissDialog() {
        mDialog?.dismiss()
    }

    fun showDialog(title: String) {
        if (mDialog != null) {
            mDialog?.show()
        } else {
            mDialog = MaterialDialog(getBaseActivity())
                    .title(text = title)
                    .cancelOnTouchOutside(false)
                    .customView(viewRes = R.layout.view_loading)
            mDialog?.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
        mBind.unbind()
    }

    /**
     * 转场动画结束回调
     */
    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        mViewModel.onEnterAnimationEnd()
    }
}