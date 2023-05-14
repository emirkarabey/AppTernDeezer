package com.emirk.appterndeezer.presentation.home.adapter

import com.emirk.appterndeezer.domain.ui_model.Genre
interface CategoryClickListener {
    fun onItemClick(genre: Genre)
}