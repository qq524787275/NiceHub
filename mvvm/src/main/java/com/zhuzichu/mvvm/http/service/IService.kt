package com.zhuzichu.mvvm.http.service

import com.zhuzichu.mvvm.AppConfig
import com.zhuzichu.mvvm.http.core.AppRetrofit

interface IService {
    fun getLoginService(token: String): LoginService {
        return AppRetrofit.getRetrofit(AppConfig.GITHUB_API_BASE_URL, token)
                .create(LoginService::class.java)
    }

    fun getUserService(token: String? = null): UserService {

        return AppRetrofit.getRetrofit(AppConfig.GITHUB_API_BASE_URL, token ?: "")
                .create(UserService::class.java)
    }
}