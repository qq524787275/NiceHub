package com.zhuzichu.nice.login.fragment

import com.zhuzichu.mvvm.base.BaseFragment
import com.zhuzichu.nice.BR
import com.zhuzichu.nice.R
import com.zhuzichu.nice.databinding.FragmentLoginBinding
import com.zhuzichu.nice.login.viewmodel.LoginViewModel

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