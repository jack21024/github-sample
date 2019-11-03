package com.jack.sample.github.model

import com.google.gson.annotations.SerializedName

data class UserRepo(
    @SerializedName("name") val name: String,
    @SerializedName("language") val language: String,
    @SerializedName("description") val description: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("stargazers_count") val stargazers_count: String
)