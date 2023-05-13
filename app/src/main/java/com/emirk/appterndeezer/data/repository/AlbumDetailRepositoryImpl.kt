package com.emirk.appterndeezer.data.repository

import com.emirk.appterndeezer.data.remote.dto.album_detail.toDomain
import com.emirk.appterndeezer.data.remote.service.ApiService
import com.emirk.appterndeezer.domain.repository.AlbumDetailRepository
import com.emirk.appterndeezer.domain.ui_model.Track
import javax.inject.Inject

class AlbumDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AlbumDetailRepository {
    override suspend fun getAlbumDetail(albumId: String): List<Track> {
        return apiService.getAlbumDetail(albumId).tracks.data.map {
            it.toDomain()
        }
    }
}