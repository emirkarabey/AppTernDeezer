package com.emirk.appterndeezer.data.remote.dto.album_detail

import com.google.gson.annotations.SerializedName

data class ArtistX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
)