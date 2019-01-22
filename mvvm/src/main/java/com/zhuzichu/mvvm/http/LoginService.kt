package com.zhuzichu.mvvm.http

import androidx.annotation.NonNull
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("authorizations")
    @Headers("Accept: application/json")
    abstract fun authorizations(
            @NonNull @Body authRequestModel: AuthRequestModel
    ): Observable<Response<BasicToken>>

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    abstract fun getAccessToken(
            @Query("client_id") clientId: String,
            @Query("client_secret") clientSecret: String,
            @Query("code") code: String,
            @Query("state") state: String
    ): Observable<Response<OauthToken>>
}