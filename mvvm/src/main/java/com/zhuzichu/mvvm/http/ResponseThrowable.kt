package com.zhuzichu.mvvm.http


class ResponseThrowable(throwable: Throwable, var code: Int) : Exception(throwable) {
    lateinit var msg: String

}