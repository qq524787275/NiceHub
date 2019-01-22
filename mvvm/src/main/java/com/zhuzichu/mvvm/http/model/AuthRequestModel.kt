package com.zhuzichu.mvvm.http.model

import com.google.gson.annotations.SerializedName
import com.zhuzichu.mvvm.AppConfig
import com.zhuzichu.mvvm.BuildConfig
import java.util.*

class AuthRequestModel {
    private var scopes: List<String>? = null
    private var note: String? = null
    private var noteUrl: String? = null
    @SerializedName("client_id")
    private var clientId: String? = null
    @SerializedName("client_secret")
    private var clientSecret: String? = null

    fun generate(): AuthRequestModel {
        val model = AuthRequestModel()
        model.scopes = Arrays.asList("user", "repo", "gist", "notifications")
        model.note = BuildConfig.APPLICATION_ID
        model.clientId = AppConfig.OPENHUB_CLIENT_ID
        model.clientSecret = AppConfig.OPENHUB_CLIENT_SECRET
        model.noteUrl = AppConfig.REDIRECT_URL
        return model
    }

    fun getScopes(): List<String>? {
        return scopes
    }

    fun getNote(): String? {
        return note
    }

    fun getNoteUrl(): String? {
        return noteUrl
    }

    fun getClientId(): String? {
        return clientId
    }

    fun getClientSecret(): String? {
        return clientSecret
    }
}