package com.zhuzichu.mvvm.http.model

data class UserRegistModel(
        var `data`: Data,
        var code: Int,
        var message: String
) {
    class Data
}