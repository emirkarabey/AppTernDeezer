package com.emirk.appterndeezer.data.remote.dto.album_detail

import com.google.gson.annotations.SerializedName

data class TracksResponse(
    @SerializedName("data")
    val data: List<TrackDto>
)