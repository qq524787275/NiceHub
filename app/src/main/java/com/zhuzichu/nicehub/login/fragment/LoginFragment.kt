package com.zhuzichu.nicehub.login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.orhanobut.logger.Logger
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.mvvm.global.language.LangConfig
import com.zhuzichu.mvvm.global.theme.ThemeConfig
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentLoginBinding
import com.zhuzichu.nicehub.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun setLayoutId(): Int = R.layout.fragment_login

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        mBind.lang = LangConfig
        mBind.theme = ThemeConfig
    }

    override fun initViewObservable() {
        mViewModel.uc.showUsernameError.observe(this, Observer {
            if (it) {
                layoutUsername.error = LangConfig.user_name_warning.get()
            } else {
                layoutUsername.isErrorEnabled = false
            }
        })

        mViewModel.uc.showPasswordError.observe(this, Observer {
            if (it) {
                layoutPassword.error = LangConfig.password_warning.get()
            } else {
                layoutPassword.isErrorEnabled = false
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.i("zzc----------onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.i("zzc----------onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        Logger.i("zzc----------onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Logger.i("zzc----------onDestroy")
        super.onDestroy()
    }
}