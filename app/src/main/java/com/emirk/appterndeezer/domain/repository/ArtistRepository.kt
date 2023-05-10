package com.emirk.appterndeezer.domain.repository

import com.emirk.appterndeezer.domain.ui_model.Artist

interface ArtistRepository {
    suspend fun getArtists(genreId: String): List<Artist>
}