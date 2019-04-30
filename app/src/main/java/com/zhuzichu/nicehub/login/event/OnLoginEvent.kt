package com.zhuzichu.nicehub.login.event

data class OnLoginEvent(var appid: String, var peerId: Int, var username: String, var token: String)