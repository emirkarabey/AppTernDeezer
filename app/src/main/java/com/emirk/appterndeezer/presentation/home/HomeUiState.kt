package com.emirk.appterndeezer.presentation.home

import com.emirk.appterndeezer.domain.ui_model.Genre

data class HomeUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val genre: List<Genre>? = emptyList(),
)