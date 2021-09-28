package com.zaich.githubuserapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class UserDetailModel(
    @SerializedName("login") val user_name: String?,
    val id: Int,
    val name: String,
    @SerializedName("avatar_url") val avatar: String,
    val followers: Int,
    val following: Int,
    @SerializedName("followers_url")val followersUrl: String,
    @SerializedName("following_url")val followingUrl: String,
    val company: String?,
    val location: String?,
    @SerializedName("public_repos")val publicRepos: Int
)