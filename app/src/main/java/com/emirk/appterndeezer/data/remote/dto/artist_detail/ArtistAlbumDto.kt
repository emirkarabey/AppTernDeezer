package com.emirk.appterndeezer.data.remote.dto.artist_detail

import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum
import com.google.gson.annotations.SerializedName

data class ArtistAlbumDto(
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_big")
    val cover_big: String,
    @SerializedName("cover_medium")
    val cover_medium: String,
    @SerializedName("cover_small")
    val cover_small: String,
    @SerializedName("cover_xl")
    val cover_xl: String,
    @SerializedName("explicit_lyrics")
    val explicit_lyrics: Boolean,
    @SerializedName("fans")
    val fans: Int,
    @SerializedName("genre_id")
    val genre_id: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("md5_image")
    val md5_image: String,
    @SerializedName("record_type")
    val record_type: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
)

fun ArtistAlbumDto.toDomain() = ArtistAlbum(
    cover = cover,
    cover_big = cover_big,
    cover_medium = cover_medium,
    cover_small = cover_small,
    cover_xl = cover_xl,
    explicit_lyrics = explicit_lyrics,
    fans = fans,
    genre_id = genre_id,
    id = id,
    link = link,
    md5_image = md5_image,
    record_type = record_type,
    release_date = release_date,
    title = title,
    tracklist = tracklist,
    type = type
)