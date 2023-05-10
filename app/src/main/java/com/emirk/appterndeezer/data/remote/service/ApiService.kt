package com.emirk.appterndeezer.data.remote.service

import com.emirk.appterndeezer.data.remote.dto.artist.ArtistResponse
import com.emirk.appterndeezer.data.remote.dto.category.CategoryResponse
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
}