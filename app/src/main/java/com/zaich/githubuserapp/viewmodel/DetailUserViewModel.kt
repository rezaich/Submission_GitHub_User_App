package com.zaich.githubuserapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaich.githubuserapp.database.Favorite
import com.zaich.githubuserapp.database.FavoriteDao
import com.zaich.githubuserapp.database.FavoriteRoomDatabase
//import com.zaich.githubuserapp.database.Favorite
//import com.zaich.githubuserapp.database.FavoriteDao
//import com.zaich.githubuserapp.database.FavoriteRoomDatabase
import com.zaich.githubuserapp.server.ServerInterface
import com.zaich.githubuserapp.server.ServerClient
import com.zaich.githubuserapp.model.UserDetailModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel (application: Application) : AndroidViewModel(application) {
    private val serverInterface: ServerInterface =
        ServerClient().getApiClient()!!.create(ServerInterface::class.java)
    private var favoriteRoomDatabase : FavoriteRoomDatabase? = FavoriteRoomDatabase.getFavoriteUsersDB(application)
    private var favoriteDao : FavoriteDao?
    init {
        favoriteDao = favoriteRoomDatabase?.favoriteDao()
    }

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
                Toast.makeText(getApplication(), "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDetailUser(): MutableLiveData<UserDetailModel> = detailUser

    suspend fun checkFavoriteUsers(id: Int) = favoriteDao?.checkFavoriteUsers(id)

    fun addFavoriteUsers(username: String, id: Int,url: String, avatar: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val githubFavoriteUsers = Favorite(username, id, url, avatar)
            favoriteDao?.addfavorite(githubFavoriteUsers)
            Log.d("Failure ", githubFavoriteUsers.toString())
        }
    }

    fun removeFavoriteUsers(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDao?.removeFavoriteUsers(id)
            Log.d("Failure ", id.toString())
        }
    }
}