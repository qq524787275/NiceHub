package com.zhuzichu.nicehub.login.fragment

import androidx.databinding.Observable
import com.zhuzichu.mvvm.base.BaseFragment
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

    override fun initViewObservable() {
        mViewModel.uc.showUsernameError.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(mViewModel.uc.showUsernameError.get()){
                    layoutUsername.error = getString(R.string.user_name_warning)
                }else{
                    layoutUsername.isErrorEnabled = false
                }
            }
        })

        mViewModel.uc.showPasswordError.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(mViewModel.uc.showPasswordError.get()){
                    layoutPassword.error = getString(R.string.password_warning)
                }else{
                    layoutPassword.isErrorEnabled = false
                }
            }
        })
    }
}