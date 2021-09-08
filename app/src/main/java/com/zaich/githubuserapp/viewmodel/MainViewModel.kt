package com.zaich.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaich.githubuserapp.model.UserArrayModel
import com.zaich.githubuserapp.model.UserModel
import com.zaich.githubuserapp.server.ServerClient
import com.zaich.githubuserapp.server.ServerInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val serverInterface : ServerInterface = ServerClient().getApiClient()!!.create(ServerInterface::class.java)
    private val search = MutableLiveData<ArrayList<UserModel>>()

    fun setSearch(searchQuery: String){
        Log.i("mainActivity", "setSearch: $searchQuery")
        serverInterface.getSearchEndPoint(searchQuery).enqueue(object : Callback<UserArrayModel>{
            override fun onResponse(
                call: Call<UserArrayModel>,
                response: Response<UserArrayModel>
            ) {
                Log.i("mainActivity", "onResponse: ${response.code()} ${response.body()}")
                if (response.isSuccessful) search.postValue(response.body()?.items)
                else{

                }
            }

            override fun onFailure(call: Call<UserArrayModel>, t: Throwable) {
                Log.d("Failure",t.message.toString())
                Log.i("mainActivity", "onResponse: $t")
            }
        })
    }

    fun getSearch() : MutableLiveData<ArrayList<UserModel>> = search
}