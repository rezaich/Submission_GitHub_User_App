package com.zaich.githubuserapp.database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao{
    @Insert
    suspend fun addfavorite(favorite: Favorite)

    @Query("SELECT count(*) FROM favorite_users WHERE favorite_users.id = :id")
    suspend fun checkFavoriteUsers(id: Int) : Int

    @Query("DELETE FROM favorite_users WHERE favorite_users.id = :id")
    suspend fun removeFavoriteUsers(id: Int) : Int

    @Query("SELECT * FROM favorite_users")
    fun getFavoriteUsers(): LiveData<List<Favorite>>

    @Query("SELECT * FROM favorite_users")
    fun consumerFavoriteUsers(): Cursor

}

