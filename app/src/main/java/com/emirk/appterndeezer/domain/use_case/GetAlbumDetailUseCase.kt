package com.emirk.appterndeezer.domain.use_case

import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.repository.AlbumDetailRepository
import com.emirk.appterndeezer.domain.ui_model.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAlbumDetailUseCase @Inject constructor(
    private val repository: AlbumDetailRepository
) {
    operator fun invoke(
        albumId: String
    ): Flow<Resource<List<Track>>> = flow {
        try {
            emit(Resource.Loading())
            val artistAlbums = repository.getAlbumDetail(albumId)
            emit(Resource.Success(data = artistAlbums))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}