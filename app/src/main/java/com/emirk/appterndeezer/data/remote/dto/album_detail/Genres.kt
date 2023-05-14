package com.emirk.appterndeezer.data.remote.dto.album_detail

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("data")
    val data: List<Data>
)