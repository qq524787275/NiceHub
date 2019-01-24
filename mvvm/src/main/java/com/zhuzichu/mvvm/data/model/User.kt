package com.zhuzichu.mvvm.http.model
import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("avatar_url")
    var avatarUrl: String? = "",
    @SerializedName("bio")
    var bio: Any? = Any(),
    @SerializedName("blog")
    var blog: String? = "",
    @SerializedName("collaborators")
    var collaborators: Int? = 0,
    @SerializedName("company")
    var company: Any? = Any(),
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("disk_usage")
    var diskUsage: Int? = 0,
    @SerializedName("email")
    var email: Any? = Any(),
    @SerializedName("events_url")
    var eventsUrl: String? = "",
    @SerializedName("followers")
    var followers: Int? = 0,
    @SerializedName("followers_url")
    var followersUrl: String? = "",
    @SerializedName("following")
    var following: Int? = 0,
    @SerializedName("following_url")
    var followingUrl: String? = "",
    @SerializedName("gists_url")
    var gistsUrl: String? = "",
    @SerializedName("gravatar_id")
    var gravatarId: String? = "",
    @SerializedName("hireable")
    var hireable: Any? = Any(),
    @SerializedName("html_url")
    var htmlUrl: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("location")
    var location: Any? = Any(),
    @SerializedName("login")
    var login: String? = "",
    @SerializedName("name")
    var name: Any? = Any(),
    @SerializedName("node_id")
    var nodeId: String? = "",
    @SerializedName("organizations_url")
    var organizationsUrl: String? = "",
    @SerializedName("owned_private_repos")
    var ownedPrivateRepos: Int? = 0,
    @SerializedName("plan")
    var plan: Plan? = Plan(),
    @SerializedName("private_gists")
    var privateGists: Int? = 0,
    @SerializedName("public_gists")
    var publicGists: Int? = 0,
    @SerializedName("public_repos")
    var publicRepos: Int? = 0,
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = "",
    @SerializedName("repos_url")
    var reposUrl: String? = "",
    @SerializedName("site_admin")
    var siteAdmin: Boolean? = false,
    @SerializedName("starred_url")
    var starredUrl: String? = "",
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = "",
    @SerializedName("total_private_repos")
    var totalPrivateRepos: Int? = 0,
    @SerializedName("two_factor_authentication")
    var twoFactorAuthentication: Boolean? = false,
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("url")
    var url: String? = ""
)

data class Plan(
    @SerializedName("collaborators")
    var collaborators: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("private_repos")
    var privateRepos: Int? = 0,
    @SerializedName("space")
    var space: Int? = 0
)