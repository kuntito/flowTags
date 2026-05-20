package com.example.flowtags.data.domain_models

data class SongTag(
    val tagId: Int,
    val tagName: String,
    val tagDescription: String,
)

val dummySongTag = SongTag(
    tagId = 0,
    tagName = "sad",
    tagDescription = "lorem ipsum"
)

val dummySongTagList = listOf(
    SongTag(
        tagId = 1,
        tagName = "234",
        tagDescription = "lorem ipsum"
    ),
    SongTag(
        tagId = 2,
        tagName = "rap",
        tagDescription = "lorem ipsum"
    ),
    SongTag(
        tagId = 3,
        tagName = "mist",
        tagDescription = "lorem ipsum"
    ),
)
