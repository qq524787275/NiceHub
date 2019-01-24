package com.zhuzichu.nicehub.login.fragment

import androidx.lifecycle.Observer
import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.mvvm.global.font.FontConfig
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
        mBind.font = FontConfig
        mBind.theme = ThemeConfig
    }

    override fun initViewObservable() {
        mViewModel.uc.showUsernameError.observe(this, Observer {
            if (it) {
                layoutUsername.error = FontConfig.user_name_warning.get()
            } else {
                layoutUsername.isErrorEnabled = false
            }
        })

        mViewModel.uc.showPasswordError.observe(this, Observer {
            if (it) {
                layoutPassword.error = FontConfig.password_warning.get()
            } else {
                layoutPassword.isErrorEnabled = false
            }
        })
    }
}