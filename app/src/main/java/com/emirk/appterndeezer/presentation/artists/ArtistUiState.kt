package com.emirk.appterndeezer.presentation.artists

import com.emirk.appterndeezer.domain.ui_model.Artist

data class ArtistUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val artist: List<Artist>? = emptyList(),
)