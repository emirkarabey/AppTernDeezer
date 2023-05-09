package com.emirk.appterndeezer.data.remote.service

import com.emirk.appterndeezer.data.remote.dto.CategoryResponse
import com.emirk.appterndeezer.util.Constants.CATEGORY_END_POINT
import retrofit2.http.GET

interface ApiService {

    @GET(CATEGORY_END_POINT)
    suspend fun getCategories(
    ): CategoryResponse
}