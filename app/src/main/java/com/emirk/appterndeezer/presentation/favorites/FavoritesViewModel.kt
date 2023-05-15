package com.emirk.appterndeezer.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.ui_model.Track
import com.emirk.appterndeezer.domain.use_case.DeleteFavoriteUseCase
import com.emirk.appterndeezer.domain.use_case.GetFavoritesUseCase
import com.emirk.appterndeezer.presentation.album_detail.TrackUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TrackUiState())
    val uiState: StateFlow<TrackUiState> = _uiState.asStateFlow()

    fun getFavorites() = viewModelScope.launch(Dispatchers.IO) {
        getFavoritesUseCase.invoke().collect { result ->
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
                        state.copy(albumSong = result.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun deleteFavorite(track: Track) = viewModelScope.launch {
        track.isFav = false
        deleteFavoriteUseCase.invoke(track = track)
    }

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }
}