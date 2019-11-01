package com.jack.sample.github.model

import com.google.gson.annotations.SerializedName

data class UserRepo(
    @SerializedName("description") val description: String,
    @SerializedName("updated_at") val updated_at: String
)