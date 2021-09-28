package com.zaich.githubuserapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "favorite_users"
)
data class Favorite (
    val login: String,
    @PrimaryKey
    val id: Int,
    val html_url: String,
    @SerializedName("avatar_url") val avatarUrl: String
)