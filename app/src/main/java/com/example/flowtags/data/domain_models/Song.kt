package com.example.flowtags.data.domain_models

data class Song(
    val songId: Int,
    val songTitle: String,
    val artistStr: String,
)

val dummySong = Song(
    songId = 0,
    songTitle = "Design",
    artistStr = "Olivetheboy",
)
