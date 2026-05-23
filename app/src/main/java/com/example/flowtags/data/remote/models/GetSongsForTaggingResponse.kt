package com.example.flowtags.data.remote.models

import com.example.flowtags.data.domain_models.SongForTagging

data class SongForTaggingApi(
    val songId: Int,
    val songTitle: String,
    val artistStr: String,
    val albumArtUrl: String,
)

data class GetSongsForTaggingResponse(
    val success: Boolean,
    val tagName: String?,
    val tagDescription: String?,
    val itemCount: Int?,
    val songsForTagging: List<SongForTaggingApi>?,
    val debug: Map<String, String>?
)


fun SongForTaggingApi.toSongForTagging() = SongForTagging(
    songId = songId,
    songTitle = songTitle,
    artistStr = artistStr,
    albumArtUrl = albumArtUrl,
)