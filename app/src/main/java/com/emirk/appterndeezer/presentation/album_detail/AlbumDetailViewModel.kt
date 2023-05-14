package com.emirk.appterndeezer.presentation.album_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.ui_model.Track
import com.emirk.appterndeezer.domain.use_case.AddFavoriteUseCase
import com.emirk.appterndeezer.domain.use_case.DeleteFavoriteUseCase
import com.emirk.appterndeezer.domain.use_case.GetAlbumDetailUseCase
import com.emirk.appterndeezer.domain.use_case.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val getAlbumDetailUseCase: GetAlbumDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrackUiState())
    val uiState: StateFlow<TrackUiState> = _uiState.asStateFlow()

    private val _uiStateFav = MutableStateFlow(TrackUiState())
    val uiStateFav: StateFlow<TrackUiState> = _uiState.asStateFlow()

    val trackList: MutableLiveData<List<Track>?> = MutableLiveData()
    private val favoriteList: MutableLiveData<List<Track>?> = MutableLiveData()

    fun getAlbumDetail(albumId: String) = viewModelScope.launch {
        getAlbumDetailUseCase.invoke(albumId).collect { result ->
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
                        isFav(result.data)
                        state.copy(albumSong = result.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun addFavorite(track: Track) = viewModelScope.launch {
        track.isFav = true
        addFavoriteUseCase.invoke(track = track)
    }

    fun deleteFavorite(track: Track) = viewModelScope.launch {
        track.isFav = false
        deleteFavoriteUseCase.invoke(track = track)
    }

    fun getFavorite() = viewModelScope.launch(Dispatchers.IO) {
        getFavoritesUseCase.invoke().collect { result ->
            when (result) {
                is Resource.Error -> {
                    _uiStateFav.update { state ->
                        state.copy(userMessage = result.message)
                    }
                }
                is Resource.Loading -> {
                    _uiStateFav.update { state ->
                        state.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    _uiStateFav.update { state ->
                        favoriteList.postValue(result.data)
                        state.copy(albumSong = result.data, isLoading = false)
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

    fun isFav(trackListLiveData: List<Track>?) = viewModelScope.launch(Dispatchers.IO) {
        getFavorite()
        favoriteList.value?.forEach { fav ->
            trackListLiveData?.forEach { track ->
                if (fav.title.equals(track.title)) {
                    track.isFav = true
                }
            }
        }
    }
}