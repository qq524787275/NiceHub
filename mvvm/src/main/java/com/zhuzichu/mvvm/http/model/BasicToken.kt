package com.zhuzichu.mvvm.http.model

import com.google.gson.annotations.SerializedName

data class BasicToken(
    @SerializedName("app")
    var app: App? = App(),
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("fingerprint")
    var fingerprint: String? = "",
    @SerializedName("hashed_token")
    var hashedToken: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("note")
    var note: String? = "",
    @SerializedName("note_url")
    var noteUrl: String? = "",
    @SerializedName("scopes")
    var scopes: List<String?>? = listOf(),
    @SerializedName("token")
    var token: String? = "",
    @SerializedName("token_last_eight")
    var tokenLastEight: String? = "",
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("url")
    var url: String? = ""
)

data class App(
    @SerializedName("client_id")
    var clientId: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("url")
    var url: String? = ""
)