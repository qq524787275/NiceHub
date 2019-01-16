package com.zhuzichu.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.trello.rxlifecycle3.components.support.RxFragment
import androidx.lifecycle.ViewModelProviders
import com.orhanobut.logger.Logger
import java.lang.reflect.ParameterizedType
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView


/**
 * Created by wb.zhuzichu18 on 2019/1/15.
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : RxFragment() {
    private lateinit var mBind: V
    private lateinit var mViewModel: VM
    private var mDialog: MaterialDialog? = null
    abstract fun setLayoutId(): Int
    abstract fun bindVariableId(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.i(this.javaClass.simpleName + "---创建了")
        val root = inflater.inflate(setLayoutId(), container, false)
        mBind = DataBindingUtil.bind(root)!!
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) mViewModel = ViewModelProviders.of(this).get(type.actualTypeArguments[1] as Class<VM>)
        mBind.setVariable(bindVariableId(), mViewModel)
        lifecycle.addObserver(mViewModel)
        mViewModel.injectLifecycleProvider(this)
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registorUIChangeLiveDataCallBack()
    }

    //注册ViewModel与View的契约UI回调事件
    private fun registorUIChangeLiveDataCallBack() {
        //加载对话框显示
        mViewModel.getUC().getShowDialogEvent().observe(this, Observer { title -> view?.postDelayed({ showDialog(title) }, 300) })
        //加载对话框消失
        mViewModel.getUC().getDismissDialogEvent().observe(this, Observer { v -> { dismissDialog() } })

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
                    .customView(viewRes = R.layout.view_loading)
            mDialog?.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
        mBind.unbind()
    }


    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    fun navigate(action: Int) {
        getBaseActivity().mNavController.navigate(action)
    }
}