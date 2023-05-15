package com.emirk.appterndeezer.presentation.favorites.adapter

import com.emirk.appterndeezer.domain.ui_model.Track

interface FavoritesItemClickListener {
    fun onItemClick(track: Track)
    fun onFavItemClick(track: Track, position: Int)
}