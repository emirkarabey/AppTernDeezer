package com.emirk.appterndeezer.domain.repository

import com.emirk.appterndeezer.domain.ui_model.Genre

interface GenresRepository {
    suspend fun getGenres(): List<Genre>
}