package com.zhuzichu.mvvm.http.core

import androidx.annotation.NonNull
import com.zhuzichu.mvvm.AppConfig
import com.zhuzichu.mvvm.BuildConfig
import com.zhuzichu.mvvm.http.interceptor.logging.Level
import com.zhuzichu.mvvm.http.interceptor.logging.LoggingInterceptor
import com.zhuzichu.mvvm.utils.getHttpImageCacheDir
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.platform.Platform
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

    private fun createRetrofit(@NonNull baseUrl: String, isJson: Boolean? = true) {
        val timeOut = AppConfig.HTTP_TIME_OUT
        val cache = Cache(getHttpImageCacheDir(),
                AppConfig.HTTP_MAX_CACHE_SIZE)

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(timeOut.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(BaseInterceptor())
                .addNetworkInterceptor(NetworkBaseInterceptor())
                .addInterceptor(LoggingInterceptor
                        .Builder()//构建者模式
                        .loggable(BuildConfig.DEBUG) //是否开启日志打印
                        .setLevel(Level.BASIC) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("Request") // request的Tag
                        .response("Response")// Response的Tag
                        .addHeader("log-header", "I am the log request header.") // 添加打印头, 注意 key 和 value 都不能是中文
                        .build()
                )
                .cache(cache)
                .build()

        val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)

        if (isJson!!) {
            builder.addConverterFactory(GsonConverterFactory.create())
        } else {
            builder.addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
        }

        retrofitMap["$baseUrl-$isJson"] = builder.build()
    }

    fun getRetrofit(baseUrl: String, token: String? = null, isJson: Boolean? = true): Retrofit {
        this.token = token
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

            if (!token.isNullOrBlank()) {
                val auth = if (token?.startsWith("Basic")!!) token else "token $token"
                request = request.newBuilder()
                        .addHeader("Authorization", auth)
                        .build()
            }

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