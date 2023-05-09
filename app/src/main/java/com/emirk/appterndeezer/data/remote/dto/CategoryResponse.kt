package com.emirk.appterndeezer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val data: List<CategoryDto>
)