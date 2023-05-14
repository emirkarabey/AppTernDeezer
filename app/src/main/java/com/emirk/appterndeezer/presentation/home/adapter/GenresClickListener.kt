package com.emirk.appterndeezer.presentation.home.adapter

import com.emirk.appterndeezer.domain.ui_model.Genre
interface GenresClickListener {
    fun onItemClick(genre: Genre)
}