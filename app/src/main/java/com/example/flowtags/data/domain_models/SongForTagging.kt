package com.example.flowtags.data.domain_models

data class SongForTagging(
    val songId: Int,
    val songTitle: String,
    val artistStr: String,
    val albumArtUrl: String,
)

const val aaSize = 320
val dummySongForTagging = SongForTagging(
    songId = 1,
    songTitle = "365 days",
    artistStr = "Tml Vibez",
    albumArtUrl = "https://picsum.photos/$aaSize/$aaSize"
)