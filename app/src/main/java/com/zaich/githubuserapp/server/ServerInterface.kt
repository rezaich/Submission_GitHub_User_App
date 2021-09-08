package com.zaich.githubuserapp.server

import com.zaich.githubuserapp.BuildConfig.GITHUB_TOKEN
import com.zaich.githubuserapp.model.UserDetailModel
import com.zaich.githubuserapp.model.UserArrayModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerInterface {

    @GET("search/users")
    @Headers("Authorization: $GITHUB_TOKEN ")
    fun getSearchEndPoint(@Query("q")searchQuery: String):Call<UserArrayModel>

    @GET("users/{username}")
    @Headers("Authorization: $GITHUB_TOKEN")
    fun getDetailUser(@Path("username") username:String):Call<UserDetailModel>
}