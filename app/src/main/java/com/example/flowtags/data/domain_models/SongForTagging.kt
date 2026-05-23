package com.example.flowtags.data.domain_models

data class SongForTagging(
    val songId: Int,
    val songTitle: String,
    val artistStr: String,
    val albumArtUrl: String,
)