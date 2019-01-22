package com.zhuzichu.mvvm.http.core

import androidx.annotation.NonNull
import com.zhuzichu.mvvm.App
import com.zhuzichu.mvvm.AppConfig
import com.zhuzichu.mvvm.utils.FileUtil
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

object AppRetrofit {

    private val TAG = "AppRetrofit"
    private val retrofitMap = HashMap<String, Retrofit>()
    private var token: String? = null

    private fun createRetrofit(@NonNull baseUrl: String, isJson: Boolean) {
        val timeOut = AppConfig.HTTP_TIME_OUT
        val cache = Cache(FileUtil.getHttpImageCacheDir(App.getContext()),
                AppConfig.HTTP_MAX_CACHE_SIZE)

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(timeOut.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(BaseInterceptor())
                .addNetworkInterceptor(NetworkBaseInterceptor())
                .cache(cache)
                .build()

        val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)

        if (isJson) {
            builder.addConverterFactory(GsonConverterFactory.create())
        } else {
            builder.addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
        }

        retrofitMap["$baseUrl-$isJson"] = builder.build()
    }

    /**
     * 拦截器
     */
    private class BaseInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(@NonNull chain: Interceptor.Chain): Response {
            var request = chain.request()


            return chain.proceed(request)
        }
    }

    /**
     * 网络请求拦截器
     */
    private class NetworkBaseInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(@NonNull chain: Interceptor.Chain): Response {

            var request = chain.request()


            return chain.proceed(request)

        }
    }
}