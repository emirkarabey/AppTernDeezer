package com.emirk.appterndeezer.presentation.artist_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.use_case.GetArtistAlbumsUseCase
import com.emirk.appterndeezer.domain.use_case.GetArtistDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase,
    private val getArtistAlbumsUseCase: GetArtistAlbumsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArtistDetailUiState())
    val uiState: StateFlow<ArtistDetailUiState> = _uiState.asStateFlow()

    private val _uiStateAlbums = MutableStateFlow(ArtistAlbumsUiState())
    val uiStateAlbums: StateFlow<ArtistAlbumsUiState> = _uiStateAlbums.asStateFlow()


    fun getArtistDetail(artistId: String) = viewModelScope.launch {
        getArtistDetailUseCase.invoke(artistId).collect { result ->
            when (result) {
                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(userMessage = result.message)
                    }
                }
                is Resource.Loading -> {
                    _uiState.update { state ->
                        state.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(artistDetail = result.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }

    fun getArtistAlbums(artistId: String) = viewModelScope.launch {
        getArtistAlbumsUseCase.invoke(artistId).collect { result ->
            when (result) {
                is Resource.Error -> {
                    _uiStateAlbums.update { state ->
                        state.copy(userMessage = result.message)
                    }
                }
                is Resource.Loading -> {
                    _uiStateAlbums.update { state ->
                        state.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    _uiStateAlbums.update { state ->
                        state.copy(artistAlbums = result.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun userMessageShownAlbums() {
        _uiStateAlbums.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }
}