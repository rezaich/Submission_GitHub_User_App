package com.zaich.githubuserapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("login") val username: String,
    val id: Int,
    val html_url: String,
    @SerializedName("avatar_url") val avatar: String,

): Parcelable
