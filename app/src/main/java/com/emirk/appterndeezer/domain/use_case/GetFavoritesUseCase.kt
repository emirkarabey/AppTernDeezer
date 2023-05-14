package com.emirk.appterndeezer.domain.use_case

import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.repository.FavoriteRepository
import com.emirk.appterndeezer.domain.ui_model.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
    ): Flow<Resource<List<Track>>> = flow {
        try {
            emit(Resource.Loading())
            val tracks = repository.getFavorites()
            emit(Resource.Success(data = tracks))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}