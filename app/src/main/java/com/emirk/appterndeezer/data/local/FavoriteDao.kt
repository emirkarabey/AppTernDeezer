package com.emirk.appterndeezer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emirk.appterndeezer.data.local.entity.TrackEntity

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(track: TrackEntity)

    @Update
    suspend fun update(track: TrackEntity)

    @Query("DELETE FROM track WHERE title=:trackTitle")
    suspend fun deleteTrack(trackTitle: String)

    @Query("SELECT * FROM track")
    fun getAllTracks(): List<TrackEntity>
}