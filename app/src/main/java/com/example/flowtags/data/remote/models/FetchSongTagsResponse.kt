package com.example.flowtags.data.remote.models

data class SongTagApi (
    val tagId: Int,
    val tagName: String,
    val tagDescription: String,
)

data class FetchSongTagsResponse(
    val success: Boolean,
    val tagCount: Int,
    val songTagTypes: List<SongTagApi>?,
    val debug: Map<String, String>?
)