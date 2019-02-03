package com.zhuzichu.mvvm.http.service

import androidx.annotation.NonNull
import com.zhuzichu.mvvm.data.model.Event
import com.zhuzichu.mvvm.http.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface UserService {
    @NonNull
    @GET("user")
    abstract fun getPersonInfo(
            @Header("forceNetWork") forceNetWork: Boolean
    ): Observable<User>

    @NonNull
    @GET("users/{user}")
    abstract fun getUser(
            @Header("forceNetWork") forceNetWork: Boolean,
            @Path("user") user: String
    ): Observable<User>

    @NonNull
    @GET("users/{user}/received_events")
    abstract fun getNewsEvent(
            @Header("forceNetWork") forceNetWork: Boolean,
            @Path("user") user: String,
            @Query("page") page: Int
    ): Observable<ArrayList<Event>>
}