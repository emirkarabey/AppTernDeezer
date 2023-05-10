package com.emirk.appterndeezer.domain.repository

import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum

interface ArtistAlbumsRepository {
    suspend fun getArtistAlbums(artistId: String): List<ArtistAlbum>
}