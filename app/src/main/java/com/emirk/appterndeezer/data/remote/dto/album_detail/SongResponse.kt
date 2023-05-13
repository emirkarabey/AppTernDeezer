package com.emirk.appterndeezer.data.remote.dto.album_detail

import com.google.gson.annotations.SerializedName

data class SongResponse(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("contributors")
    val contributors: List<Contributor>,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_big")
    val cover_big: String,
    @SerializedName("cover_medium")
    val cover_medium: String,
    @SerializedName("cover_small")
    val cover_small: String,
    @SerializedName("cover_xl")
    val cover_xl: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicit_content_cover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicit_content_lyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicit_lyrics: Boolean,
    @SerializedName("fans")
    val fans: Int,
    @SerializedName("genre_id")
    val genre_id: Int,
    @SerializedName("genres")
    val genres: Genres,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("md5_image")
    val md5_image: String,
    @SerializedName("nb_tracks")
    val nb_tracks: Int,
    @SerializedName("record_type")
    val record_type: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("share")
    val share: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("tracks")
    val tracks: TracksResponse,
    @SerializedName("type")
    val type: String,
    @SerializedName("upc")
    val upc: String
)