package com.zhuzichu.nicehub.login.fragment

import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nicehub.BR
import com.zhuzichu.nicehub.R
import com.zhuzichu.nicehub.databinding.FragmentLoginBinding
import com.zhuzichu.nicehub.login.viewmodel.LoginViewModel

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun setLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun bindVariableId(): Int {
       return BR.viewModel
}
}