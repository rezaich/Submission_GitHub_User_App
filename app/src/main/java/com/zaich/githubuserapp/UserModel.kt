package com.zaich.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val name : String,
    val user_name : String,
    val avatar : Int,
    val follower : String ,
    val following : String ,
    val repository : String,
    val location : String,
    val company : String
): Parcelable
