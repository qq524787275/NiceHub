package com.zhuzichu.mvvm.http.service

import com.zhuzichu.mvvm.AppConfig
import com.zhuzichu.mvvm.http.core.AppRetrofit

interface IService {
    fun getLoginService(token: String): LoginService {
        return AppRetrofit.getRetrofit(AppConfig.GITHUB_API_BASE_URL)
                .create(LoginService::class.java)
    }

    fun getUserService(token: String? = null): UserService {
        return AppRetrofit.getRetrofit(AppConfig.GITHUB_API_BASE_URL)
                .create(UserService::class.java)
    }

    fun getIMService(token: String? = null): IMService {
        return AppRetrofit.getRetrofit(AppConfig.IM_LOGINSERVER)
                .create(IMService::class.java)
    }
}