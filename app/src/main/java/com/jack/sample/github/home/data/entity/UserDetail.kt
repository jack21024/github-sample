package com.jack.sample.github.home.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDetail(
    @SerializedName("id") var id: Long,
    @SerializedName("login") var login: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
    @SerializedName("site_admin") var isSiteAdmin: Boolean = false,
    @SerializedName("bio") var bio: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("location") var location: String = "",
    @SerializedName("blogUrl") var blogUrl: String = "",
    @SerializedName("email") val email: String
): Serializable