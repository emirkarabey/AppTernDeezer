package com.emirk.appterndeezer.domain.use_case

import com.emirk.appterndeezer.common.Resource
import com.emirk.appterndeezer.domain.repository.ArtistAlbumsRepository
import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArtistAlbumsUseCase @Inject constructor(
    private val repository: ArtistAlbumsRepository
) {
    operator fun invoke(
        artistId: String
    ): Flow<Resource<List<ArtistAlbum>>> = flow {
        try {
            emit(Resource.Loading())
            val artistAlbums = repository.getArtistAlbums(artistId)
            emit(Resource.Success(data = artistAlbums))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        }
    }
}