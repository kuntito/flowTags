package com.example.flowtags.data.remote.models

data class AddNotTagSongResponse(
    val success: Boolean,
    val clientErrorMessage: String?,
    val debug: Map<String, String>?
)