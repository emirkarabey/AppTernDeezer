package com.emirk.appterndeezer.data.remote.dto.category

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("data")
    val data: List<CategoryDto>
)