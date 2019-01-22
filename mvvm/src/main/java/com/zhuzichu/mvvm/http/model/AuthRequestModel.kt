package com.zhuzichu.mvvm.http.model

import com.google.gson.annotations.SerializedName
import com.zhuzichu.mvvm.BuildConfig
import java.util.*

data class AuthRequestModel(
        var scopes: List<String?>? = listOf(),
        var note: String? = "",
        var noteUrl: String? = "",
        @SerializedName("client_id")
        var clientId: String? = "",
        @SerializedName("client_secret")
        var clientSecret: String? = ""
) {

    companion object {
        fun generate(): AuthRequestModel {
            val model = AuthRequestModel()
            model.scopes = Arrays.asList("user", "repo", "gist", "notifications")
            model.note = BuildConfig.APPLICATION_ID
            model.clientId = BuildConfig.NICEHUB_CLIENT_ID
            model.clientSecret = BuildConfig.NICEHUB_CLIENT_SECRET
            model.noteUrl = BuildConfig.REDIRECT_URL
            return model
        }
    }

}