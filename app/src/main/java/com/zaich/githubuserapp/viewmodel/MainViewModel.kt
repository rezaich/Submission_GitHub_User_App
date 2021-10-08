package com.zaich.githubuserapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.zaich.githubuserapp.database.SettingPreferences
import com.zaich.githubuserapp.model.UserArrayModel
import com.zaich.githubuserapp.model.UserModel
import com.zaich.githubuserapp.server.ServerClient
import com.zaich.githubuserapp.server.ServerInterface
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (application: Application) : AndroidViewModel(application) {
    private val serverInterface: ServerInterface =
        ServerClient().getApiClient()!!.create(ServerInterface::class.java)
    private val search = MutableLiveData<ArrayList<UserModel>>()

    fun setSearch(searchQuery: String) {
        Log.i("mainActivity", "setSearch: $searchQuery")
        serverInterface.getSearchEndPoint(searchQuery).enqueue(object : Callback<UserArrayModel> {
            override fun onResponse(
                call: Call<UserArrayModel>,
                response: Response<UserArrayModel>
            ) {
                if (response.isSuccessful) search.postValue(response.body()?.items)
            }

            override fun onFailure(call: Call<UserArrayModel>, t: Throwable) {
                Log.d("Failure", t.message.toString())
                Toast.makeText(getApplication(), "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getSearch(): MutableLiveData<ArrayList<UserModel>> = search
}