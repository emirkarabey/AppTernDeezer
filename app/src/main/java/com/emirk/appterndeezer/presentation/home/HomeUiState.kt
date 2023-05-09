package com.emirk.appterndeezer.presentation.home

import com.emirk.appterndeezer.domain.ui_model.Category

data class HomeUiState(
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val category: List<Category>? = emptyList(),
)