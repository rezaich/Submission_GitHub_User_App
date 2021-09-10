package com.zaich.githubuserapp.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerClient {
    var BASE_URL: String = "https://api.github.com/"
    var retrofit: Retrofit? = null

    fun getApiClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}