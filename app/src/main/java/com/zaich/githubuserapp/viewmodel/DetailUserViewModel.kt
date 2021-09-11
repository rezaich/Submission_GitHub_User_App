package com.zaich.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaich.githubuserapp.server.ServerInterface
import com.zaich.githubuserapp.server.ServerClient
import com.zaich.githubuserapp.model.UserDetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    private val serverInterface: ServerInterface =
        ServerClient().getApiClient()!!.create(ServerInterface::class.java)
    private val detailUser = MutableLiveData<UserDetailModel>()

    fun setDetailUser(username: String) {
        serverInterface.getDetailUser(username).enqueue(object : Callback<UserDetailModel> {
            override fun onResponse(
                call: Call<UserDetailModel>,
                response: Response<UserDetailModel>
            ) {
                if (response.isSuccessful)detailUser.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetailModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
    }

    fun getDetailUser(): MutableLiveData<UserDetailModel> = detailUser
}