package com.jack.sample.github.model

import com.google.gson.annotations.SerializedName

data class UserIntro(
    @SerializedName("login") val login: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)