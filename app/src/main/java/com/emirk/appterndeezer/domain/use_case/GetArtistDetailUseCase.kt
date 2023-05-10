package com.emirk.appterndeezer.domain.use_case

import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.repository.ArtistDetailRepository
import com.emirk.appterndeezer.domain.ui_model.ArtistDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArtistDetailUseCase @Inject constructor(
    private val repository: ArtistDetailRepository
) {
    operator fun invoke(
        artistId: String
    ): Flow<Resource<ArtistDetail>> = flow {
        try {
            emit(Resource.Loading())
            val artistDetail = repository.getArtistDetail(artistId)
            emit(Resource.Success(data = artistDetail))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}