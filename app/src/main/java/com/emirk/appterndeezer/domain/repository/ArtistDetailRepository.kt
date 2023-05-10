package com.emirk.appterndeezer.domain.repository

import com.emirk.appterndeezer.domain.ui_model.ArtistDetail

interface ArtistDetailRepository {
    suspend fun getArtistDetail(artistId: String): ArtistDetail
}