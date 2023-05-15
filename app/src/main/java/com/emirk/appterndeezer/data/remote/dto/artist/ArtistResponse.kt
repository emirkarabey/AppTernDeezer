package com.emirk.appterndeezer.data.remote.dto.artist

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("data")
    val `data`: List<ArtistDto>
)