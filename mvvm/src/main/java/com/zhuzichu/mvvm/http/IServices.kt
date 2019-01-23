package com.zhuzichu.mvvm.http

import com.zhuzichu.mvvm.AppConfig
import com.zhuzichu.mvvm.http.core.AppRetrofit

interface IServices {
    fun getLoginService(token: String): LoginService{
        return AppRetrofit.getRetrofit(AppConfig.GITHUB_API_BASE_URL, token)
                ?.create(LoginService::class.java)!!
    }
}