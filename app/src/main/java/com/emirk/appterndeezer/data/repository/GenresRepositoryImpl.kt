package com.emirk.appterndeezer.data.repository

import com.emirk.appterndeezer.data.remote.dto.category.toDomain
import com.emirk.appterndeezer.data.remote.service.ApiService
import com.emirk.appterndeezer.domain.repository.GenresRepository
import com.emirk.appterndeezer.domain.ui_model.Genre
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GenresRepository {
    override suspend fun getGenres(): List<Genre> {
        return apiService.getGenres().data.map {
            it.toDomain()
        }
    }
}