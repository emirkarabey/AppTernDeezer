package com.emirk.appterndeezer.data.remote.dto.album_detail

import com.emirk.appterndeezer.domain.ui_model.Track
import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("album")
    val album: Album,
    @SerializedName("artist")
    val artist: ArtistX,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicit_content_cover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicit_content_lyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicit_lyrics: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("md5_image")
    val md5_image: String,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("readable")
    val readable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val title_short: String,
    @SerializedName("title_version")
    val title_version: String,
    @SerializedName("type")
    val type: String
)

fun TrackDto.toDomain() = Track(
    trackImage = album.cover_xl,
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
    isFav = null
)