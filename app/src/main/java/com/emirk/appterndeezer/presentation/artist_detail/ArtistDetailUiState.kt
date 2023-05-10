package com.emirk.appterndeezer.presentation.artist_detail

import com.emirk.appterndeezer.domain.ui_model.ArtistDetail

data class ArtistDetailUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val artistDetail: ArtistDetail? = null
)