package com.emirk.appterndeezer.presentation.artist_detail

import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum

data class ArtistAlbumsUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val artistAlbums: List<ArtistAlbum>? = emptyList()
)