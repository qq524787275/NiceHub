package com.zhuzichu.mvvm.data.model

data class UserModel(
        val startCursor: String? = null,
        val endCursor: String? = null,
        val hasNextPage: Boolean = false,
        val hasPreviousPage: Boolean = false
)