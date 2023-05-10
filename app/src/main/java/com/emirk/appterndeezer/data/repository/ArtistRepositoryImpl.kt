package com.emirk.appterndeezer.data.repository

import com.emirk.appterndeezer.data.remote.dto.artist.toDomain
import com.emirk.appterndeezer.data.remote.service.ApiService
import com.emirk.appterndeezer.domain.repository.ArtistRepository
import com.emirk.appterndeezer.domain.ui_model.Artist
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ArtistRepository {
    override suspend fun getArtists(genreId: String): List<Artist> {
        return apiService.getArtists(genreId).data.map {
            it.toDomain()
        }
    }
}