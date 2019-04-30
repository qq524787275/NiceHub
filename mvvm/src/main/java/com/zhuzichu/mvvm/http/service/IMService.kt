package com.zhuzichu.mvvm.http.service

import androidx.annotation.NonNull
import com.zhuzichu.mvvm.http.model.UserLoginModel
import com.zhuzichu.mvvm.http.model.UserRegistModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IMService {
    @POST("api/checkLogin")
    @FormUrlEncoded
    fun userLogin(
            @NonNull @Field("username") username: String,
            @NonNull @Field("password") password: String
    ): Observable<UserLoginModel>

    @POST("api/reg")
    @FormUrlEncoded
    fun userRegist(
            @NonNull @Field("username") username: String,
            @NonNull @Field("password") password: String,
            @NonNull @Field("code") code: String,
            @NonNull @Field("nickname") nickname: String
    ): Observable<UserRegistModel>
}
