package com.emirk.appterndeezer.domain.repository

import com.emirk.appterndeezer.domain.ui_model.Track


interface AlbumDetailRepository {
    suspend fun getAlbumDetail(albumId: String): List<Track>
}