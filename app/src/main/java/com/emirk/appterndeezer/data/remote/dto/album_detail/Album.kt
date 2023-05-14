package com.emirk.appterndeezer.data.remote.dto.album_detail

import com.google.gson.annotations.SerializedName

data class Album(
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
    @SerializedName("id")
    val id: Int,
    @SerializedName("md5_image")
    val md5_image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
)