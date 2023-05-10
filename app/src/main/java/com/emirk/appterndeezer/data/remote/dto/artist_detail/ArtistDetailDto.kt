package com.emirk.appterndeezer.data.remote.dto.artist_detail

import com.emirk.appterndeezer.domain.ui_model.ArtistDetail
import com.google.gson.annotations.SerializedName

data class ArtistDetailDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nb_album")
    val nb_album: Int,
    @SerializedName("nb_fan")
    val nb_fan: Int,
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
    @SerializedName("share")
    val share: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
)

fun ArtistDetailDto.toDomain() = ArtistDetail(
    id = id,
    link = link,
    name = name,
    nb_album = nb_album,
    nb_fan = nb_fan,
    picture = picture,
    picture_big = picture_big,
    picture_medium = picture_medium,
    picture_small = picture_small,
    picture_xl = picture_xl,
    radio = radio,
    share = share,
    tracklist = tracklist,
    type = type
)