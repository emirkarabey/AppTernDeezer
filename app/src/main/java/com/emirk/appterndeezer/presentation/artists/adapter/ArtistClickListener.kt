package com.emirk.appterndeezer.presentation.artists.adapter

import com.emirk.appterndeezer.domain.ui_model.Artist

interface ArtistClickListener {
    fun onItemClick(artist: Artist)
}