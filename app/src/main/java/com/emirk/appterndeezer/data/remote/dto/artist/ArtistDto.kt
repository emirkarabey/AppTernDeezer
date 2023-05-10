package com.emirk.appterndeezer.data.remote.dto.artist

import com.emirk.appterndeezer.domain.ui_model.Artist
import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("picture_big")
    val picture_big: String,
    @SerializedName("picture_medium")
    val picture_medium: String,
    @SerializedName("picture_small")
    val picture_small: String,
    @SerializedName("picture_xl")
    val picture_xl: String,
    @SerializedName("radio")
    val radio: Boolean,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
)

fun ArtistDto.toDomain() = Artist(
    id = id,
    name = name,
    picture = picture,
    picture_big = picture_big,
    picture_medium = picture_medium,
    picture_small = picture_small,
    picture_xl = picture_xl,
    radio = radio,
    tracklist = tracklist,
    type = type
)