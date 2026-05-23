package com.example.flowtags.data.remote.models

data class AddTagToSongResponse(
    val success: Boolean,
    val clientErrorMessage: String?,
    val debug: Map<String, String>?
)