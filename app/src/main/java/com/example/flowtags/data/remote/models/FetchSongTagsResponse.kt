package com.example.flowtags.data.remote.models

import com.example.flowtags.data.domain_models.SongTag

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


fun SongTagApi.toSongTag() = SongTag(
    tagId = tagId,
    tagName = tagName,
    tagDescription = tagDescription,
)