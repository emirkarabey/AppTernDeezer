package com.emirk.appterndeezer.data.remote.dto.album_detail

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
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
    @SerializedName("role")
    val role: String,
    @SerializedName("share")
    val share: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
)