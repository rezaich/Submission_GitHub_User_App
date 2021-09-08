package com.zaich.githubuserapp.model

import com.google.gson.annotations.SerializedName

data class UserDetailModel(
    @SerializedName("login") val user_name: String?,
    val id: Int,
    val name: String,
    @SerializedName("avatar_url") val avatar: String,
    val followers: Int,
    val following: Int,
    val followers_url: String,
    val following_url: String,
    val company: String?,
    val location: String?,
    val public_repos: Int
)