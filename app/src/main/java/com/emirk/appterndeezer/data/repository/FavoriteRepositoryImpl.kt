package com.emirk.appterndeezer.data.repository

import com.emirk.appterndeezer.data.local.FavoriteDao
import com.emirk.appterndeezer.data.local.entity.TrackEntity
import com.emirk.appterndeezer.data.local.entity.toDomain
import com.emirk.appterndeezer.domain.repository.FavoriteRepository
import com.emirk.appterndeezer.domain.ui_model.Track
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favDao: FavoriteDao
) : FavoriteRepository {
    override suspend fun getFavorites(): List<Track> {
        return favDao.getAllTracks().map {
            it.toDomain()
        }
    }

    override suspend fun addFavorite(trackEntity: TrackEntity) {
        favDao.insert(track = trackEntity)
    }

    override suspend fun deleteFavorite(trackTitle: String) {
        favDao.deleteTrack(trackTitle = trackTitle)
    }
}