package com.zhuzichu.mvvm.http


class ResponseThrowable : RuntimeException {
    lateinit var msg: String

    constructor() {}

    constructor(msg: String) {
        this.msg = msg
    }
}