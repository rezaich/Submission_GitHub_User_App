package com.zaich.githubuserapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Favorite::class],
    version = 1
)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var FAVORITE_USERS_INSTANCE: FavoriteRoomDatabase? = null

        fun getFavoriteUsersDB(context: Context): FavoriteRoomDatabase? {
            if (FAVORITE_USERS_INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class) {
                    if (FAVORITE_USERS_INSTANCE == null) FAVORITE_USERS_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteRoomDatabase::class.java,
                        "favorite_room_database"
                    ).build()
                }
            }
            return FAVORITE_USERS_INSTANCE
        }
    }
}