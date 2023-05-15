package com.emirk.appterndeezer.presentation.album_detail.adapter

import com.emirk.appterndeezer.domain.ui_model.Track

interface AlbumDetailItemClickListener {
    fun onItemClick(track: Track)
    fun onFavItemClick(track: Track, position: Int)
}