package com.emirk.appterndeezer.presentation.album_detail

import com.emirk.appterndeezer.domain.ui_model.Track

data class TrackUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val albumSong: List<Track>? = emptyList()
)