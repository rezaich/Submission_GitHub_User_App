package com.zaich.githubuserapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zaich.githubuserapp.database.Favorite
import com.zaich.githubuserapp.database.FavoriteDao
import com.zaich.githubuserapp.database.FavoriteRoomDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var favoriteDB: FavoriteRoomDatabase? = FavoriteRoomDatabase.getFavoriteUsersDB(application)
    private var favoriteDAO: FavoriteDao?=favoriteDB?.favoriteDao()

    fun getListFavoriteUsers(): LiveData<List<Favorite>>? {
        return favoriteDAO?.getFavoriteUsers()
    }
}