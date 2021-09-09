package com.zaich.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaich.githubuserapp.server.ServerClient
import com.zaich.githubuserapp.server.ServerInterface
import com.zaich.githubuserapp.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {
    private val serverInterface : ServerInterface? = ServerClient().getApiClient()?.create(
        ServerInterface::class.java)
    private val followers = MutableLiveData<ArrayList<UserModel>>()

    fun setFollowers(username: String){
        serverInterface?.getUserFollowersEndpoint(username)?.enqueue(object : Callback<ArrayList<UserModel>>{
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ) {
                if (response.isSuccessful) followers.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                Log.d("onFailure",t.message.toString())
            }
        })
    }

    fun getFollower():MutableLiveData<ArrayList<UserModel>> = followers

    private val followings  = MutableLiveData<ArrayList<UserModel>>()

    fun setFollowings(username  : String){
        serverInterface?.getUserFollowingEndpoint(username)?.enqueue(object : Callback<ArrayList<UserModel>>{
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ) {
                if (response.isSuccessful)followings.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                Log.d("onFailure",t.message.toString())
            }
        })
    }

    fun getFollowing() : MutableLiveData<ArrayList<UserModel>> = followings

}