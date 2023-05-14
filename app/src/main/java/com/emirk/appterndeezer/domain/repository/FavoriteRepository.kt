package com.emirk.appterndeezer.domain.repository

import com.emirk.appterndeezer.data.local.entity.TrackEntity
import com.emirk.appterndeezer.domain.ui_model.Track

interface FavoriteRepository {
    suspend fun getFavorites(): List<Track>
    suspend fun addFavorite(trackEntity: TrackEntity)
    suspend fun deleteFavorite(trackTitle: String)
}