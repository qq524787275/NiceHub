package com.zhuzichu.mvvm.http.service

import androidx.annotation.NonNull
import com.zhuzichu.mvvm.http.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @NonNull
    @GET("user")
    abstract fun getPersonInfo(
            @Header("forceNetWork") forceNetWork: Boolean
    ): Observable<Response<User>>

    @NonNull
    @GET("users/{user}")
    abstract fun getUser(
            @Header("forceNetWork") forceNetWork: Boolean,
            @Path("user") user: String
    ): Observable<Response<User>>
}