package com.zhuzichu.mvvm.http

import androidx.annotation.NonNull
import com.zhuzichu.mvvm.http.model.AuthRequestModel
import com.zhuzichu.mvvm.http.model.BasicToken
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @POST("authorizations")
    @Headers("Accept: application/json")
    fun authorizations(
            @NonNull @Body authRequestModel: AuthRequestModel
    ): Observable<BasicToken>

}