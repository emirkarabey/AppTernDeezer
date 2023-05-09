package com.emirk.appterndeezer.domain.repository

import com.emirk.appterndeezer.domain.ui_model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}