package com.emirk.appterndeezer.data.repository

import com.emirk.appterndeezer.data.remote.dto.toDomain
import com.emirk.appterndeezer.data.remote.service.ApiService
import com.emirk.appterndeezer.domain.repository.CategoryRepository
import com.emirk.appterndeezer.domain.ui_model.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        return apiService.getCategories().data.map {
            it.toDomain()
        }
    }
}