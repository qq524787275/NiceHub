@file:Suppress("DEPRECATION")

package com.zhuzichu.mvvm.http.core

import androidx.annotation.NonNull
import com.zhuzichu.mvvm.AppConfig
import com.zhuzichu.mvvm.http.MyGsonConverterFactory
import com.zhuzichu.mvvm.http.interceptor.HttpLoggingInterceptor
import com.zhuzichu.mvvm.utils.getHttpImageCacheDir
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level

object AppRetrofit {

    private val retrofitMap = HashMap<String, Retrofit>()

    private fun createRetrofit(@NonNull baseUrl: String, isJson: Boolean? = true) {
        val timeOut = AppConfig.HTTP_TIME_OUT
        val cache = Cache(getHttpImageCacheDir(),
                AppConfig.HTTP_MAX_CACHE_SIZE)

        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.INFO)

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(timeOut.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(BaseInterceptor())
                .addNetworkInterceptor(NetworkBaseInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()

        val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)

        if (isJson!!) {
            builder.addConverterFactory(MyGsonConverterFactory.create())
        } else {
            builder.addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
        }

        retrofitMap["$baseUrl-$isJson"] = builder.build()
    }

    fun getRetrofit(baseUrl: String, isJson: Boolean? = true): Retrofit {
        val key = "$baseUrl-$isJson"
        if (!retrofitMap.containsKey(key)) {
            createRetrofit(baseUrl, isJson)
        }
        return retrofitMap[key]!!
    }

    /**
     * 拦截器
     */
    private class BaseInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(@NonNull chain: Interceptor.Chain): Response {
            var request = chain.request()
            if (request.method() == "POST") {
                val body = request.body()
                if (body is FormBody) {
                    val bodyBuilder = FormBody.Builder()
                    //将以前的参数添加
                    for (i in 0 until body.size()) {
                        bodyBuilder.add(body.encodedName(i), body.encodedValue(i))
                    }
                    bodyBuilder.add("appid", "88888")
                    request = request.newBuilder().post(bodyBuilder.build()).build()
                }
            }

            request = request.newBuilder()
                    .build()
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