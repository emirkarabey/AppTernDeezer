package com.emirk.appterndeezer.domain.use_case

import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.repository.GenresRepository
import com.emirk.appterndeezer.domain.ui_model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: GenresRepository
) {
    operator fun invoke(
    ): Flow<Resource<List<Genre>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = repository.getGenres()
            emit(Resource.Success(data = categories))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}