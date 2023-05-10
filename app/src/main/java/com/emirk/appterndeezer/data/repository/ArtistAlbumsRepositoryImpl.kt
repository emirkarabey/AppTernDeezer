package com.emirk.appterndeezer.data.repository

import com.emirk.appterndeezer.data.remote.dto.artist_detail.toDomain
import com.emirk.appterndeezer.data.remote.service.ApiService
import com.emirk.appterndeezer.domain.repository.ArtistAlbumsRepository
import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum
import javax.inject.Inject

class ArtistAlbumsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ArtistAlbumsRepository {
    override suspend fun getArtistAlbums(artistId: String): List<ArtistAlbum> {
        return apiService.getArtistAlbums(artistId).data.map {
            it.toDomain()
        }
    }
}