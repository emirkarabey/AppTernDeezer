package com.emirk.appterndeezer.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirk.appterndeezer.data.local.FavoriteDao

@Database(
    entities = [TrackEntity::class],
    version = 1
)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}