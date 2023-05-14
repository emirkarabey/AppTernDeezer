package com.emirk.appterndeezer.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emirk.appterndeezer.domain.ui_model.Track

@Entity(tableName = "track")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "album") val albumImage: String,
    @ColumnInfo(name = "duration") val duration: Int,
    @ColumnInfo(name = "explicit_content_cover") val explicit_content_cover: Int,
    @ColumnInfo(name = "explicit_content_lyrics") val explicit_content_lyrics: Int,
    @ColumnInfo(name = "explicit_lyrics") val explicit_lyrics: Boolean,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "md5_image") val md5_image: String,
    @ColumnInfo(name = "preview") val preview: String,
    @ColumnInfo(name = "rank") val rank: Int,
    @ColumnInfo(name = "readable") val readable: Boolean,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "title_short") val title_short: String,
    @ColumnInfo(name = "title_version") val title_version: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "isFav") var isFav: Boolean
)

fun TrackEntity.toDomain() = Track(
    trackImage = albumImage,
    duration = duration,
    explicit_content_cover = explicit_content_cover,
    explicit_content_lyrics = explicit_content_lyrics,
    explicit_lyrics = explicit_lyrics,
    id = id,
    link = link,
    md5_image = md5_image,
    preview = preview,
    rank = rank,
    readable = readable,
    title = title,
    title_short = title_short,
    title_version = title_version,
    type = type,
    isFav = isFav
)