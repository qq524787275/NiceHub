package com.zhuzichu.mvvm.global.user

import androidx.databinding.ObservableField
import com.zhuzichu.mvvm.http.model.BasicToken
import com.zhuzichu.mvvm.http.model.User

object UserConfig {
    val basecToken = ObservableField<BasicToken>()
    val user = ObservableField<User>()
}