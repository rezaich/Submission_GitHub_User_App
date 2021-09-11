package com.zaich.githubuserapp.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("login") val username: String,
    val html_url: String,
    @SerializedName("avatar_url") val avatar: String?,
    val id: Int
)
