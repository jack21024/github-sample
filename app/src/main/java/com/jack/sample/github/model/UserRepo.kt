package com.jack.sample.github.model

import com.google.gson.annotations.SerializedName

data class UserRepo(

    @SerializedName("owner")
    val owner: User,

    @SerializedName("full_name")
    val full_name: String,

    @SerializedName("language")
    val language: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("updated_at")
    val updated_at: String,

    @SerializedName("stargazers_count")
    val stargazers_count: String

)