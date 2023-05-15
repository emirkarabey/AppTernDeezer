package com.emirk.appterndeezer.data.remote.dto.artist_detail

import com.google.gson.annotations.SerializedName

data class ArtistAlbumResponse(
    @SerializedName("data")
    val data: List<ArtistAlbumDto>
)