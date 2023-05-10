package com.emirk.appterndeezer.data.remote.dto.category

import com.emirk.appterndeezer.domain.ui_model.Category
import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id")
    val id: Int,
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
    @SerializedName("type")
    val type: String
)

fun CategoryDto.toDomain() = Category(
    id = id,
    name = name,
    picture = picture,
    picture_big = picture_big,
    picture_medium = picture_medium,
    picture_small = picture_small,
    picture_xl = picture_xl,
    type = type
)