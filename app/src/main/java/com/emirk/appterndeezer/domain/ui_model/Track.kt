package com.emirk.appterndeezer.domain.ui_model

import com.emirk.appterndeezer.data.local.entity.TrackEntity

data class Track(
    val trackImage: String,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: Int,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: Int,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String,
    var isFav: Boolean?
)

fun Track.toData() = TrackEntity(
    albumImage = trackImage,
    duration = duration,
    explicit_content_cover = explicit_content_cover,
    explicit_content_lyrics = explicit_content_lyrics,
    explicit_lyrics = explicit_lyrics,
    id = id,
    link = link,
    md5_image = md5_image,
    preview = preview,
    rank = rank,
    readable = readable,
    title = title,
    title_short = title_short,
    title_version = title_version,
    type = type,
    isFav = true
)