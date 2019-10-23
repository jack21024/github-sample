package com.jack.sample.github.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("url") val url:String,
    @SerializedName("repos_url") val repos_url: String
)
