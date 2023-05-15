package com.emirk.appterndeezer.domain.ui_model

data class ArtistAlbum(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val fans: Int,
    val genre_id: Int,
    val id: Int,
    val link: String,
    val md5_image: String,
    val record_type: String,
    val release_date: String,
    val title: String,
    val tracklist: String,
    val type: String
)