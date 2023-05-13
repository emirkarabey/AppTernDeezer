package com.emirk.appterndeezer.data.remote.service

import com.emirk.appterndeezer.data.remote.dto.album_detail.SongResponse
import com.emirk.appterndeezer.data.remote.dto.artist.ArtistResponse
import com.emirk.appterndeezer.data.remote.dto.artist_detail.ArtistAlbumResponse
import com.emirk.appterndeezer.data.remote.dto.artist_detail.ArtistDetailDto
import com.emirk.appterndeezer.data.remote.dto.category.CategoryResponse
import com.emirk.appterndeezer.util.Constants.ALBUM_DETAIL_END_POINT
import com.emirk.appterndeezer.util.Constants.ARTISTS_ALBUM_END_POINT
import com.emirk.appterndeezer.util.Constants.ARTISTS_DETAIL_END_POINT
import com.emirk.appterndeezer.util.Constants.ARTISTS_END_POINT
import com.emirk.appterndeezer.util.Constants.CATEGORY_END_POINT
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(CATEGORY_END_POINT)
    suspend fun getCategories(
    ): CategoryResponse

    @GET(ARTISTS_END_POINT)
    suspend fun getArtists(
        @Path("genre_id") genreId: String
    ): ArtistResponse

    @GET(ARTISTS_DETAIL_END_POINT)
    suspend fun getArtistDetail(
        @Path("artist_id") artistId: String
    ): ArtistDetailDto

    @GET(ARTISTS_ALBUM_END_POINT)
    suspend fun getArtistAlbums(
        @Path("artist_id") artistId: String
    ): ArtistAlbumResponse

    @GET(ALBUM_DETAIL_END_POINT)
    suspend fun getAlbumDetail(
        @Path("album_id") albumId: String
    ): SongResponse
}