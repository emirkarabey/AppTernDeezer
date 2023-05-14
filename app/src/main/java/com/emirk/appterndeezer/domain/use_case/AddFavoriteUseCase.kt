package com.emirk.appterndeezer.domain.use_case

import com.emirk.appterndeezer.domain.repository.FavoriteRepository
import com.emirk.appterndeezer.domain.ui_model.Track
import com.emirk.appterndeezer.domain.ui_model.toData
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(
        track: Track
    ) {
        repository.addFavorite(track.toData())
    }
}