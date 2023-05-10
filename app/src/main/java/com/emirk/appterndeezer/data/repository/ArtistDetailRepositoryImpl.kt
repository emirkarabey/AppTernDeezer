package com.emirk.appterndeezer.data.repository

import com.emirk.appterndeezer.data.remote.dto.artist_detail.toDomain
import com.emirk.appterndeezer.data.remote.service.ApiService
import com.emirk.appterndeezer.domain.repository.ArtistDetailRepository
import com.emirk.appterndeezer.domain.ui_model.ArtistDetail
import javax.inject.Inject

class ArtistDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ArtistDetailRepository {
    override suspend fun getArtistDetail(artistId: String): ArtistDetail {
        return apiService.getArtistDetail(artistId).toDomain()
    }
}