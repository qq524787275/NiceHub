package com.zhuzichu.mvvm.http.model

data class UserLoginModel(
        var `data`: Data,
        var code: Int,
        var message: String
) {
    data class Data(
            var bqmmplugin: Bqmmplugin,
            var serverinfo: Serverinfo,
            var token: String,
            var userinfo: Userinfo
    ) {
        data class Userinfo(
                var apiToken: String,
                var appId: Int,
                var avatar: String,
                var created: Int,
                var dataSign: String,
                var departId: Int,
                var domain: String,
                var email: String,
                var id: Int,
                var latitude: String,
                var longitude: String,
                var nickname: String,
                var outId: Int,
                var peerId: Int,
                var phone: String,
                var pushShieldStatus: Boolean,
                var realname: String,
                var salt: String,
                var sex: Int,
                var signInfo: String,
                var status: Int,
                var updated: Int,
                var username: String
        )

        data class Bqmmplugin(
                var appid: String,
                var appsecret: String
        )

        data class Serverinfo(
                var msfsBackup: String,
                var msfsPrior: String,
                var server_ip: String,
                var server_ip2: String,
                var server_port: Int
        )
    }
}