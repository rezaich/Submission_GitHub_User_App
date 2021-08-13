package com.zaich.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val name : String,
    val user_name : String,
    val avatar : Int
): Parcelable
