package com.jack.sample.github.home.data.entity

import com.google.gson.annotations.SerializedName

data class UserIntro(
    @SerializedName("login") val login: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)