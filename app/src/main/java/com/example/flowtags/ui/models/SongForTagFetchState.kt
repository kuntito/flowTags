package com.example.flowtags.ui.models

import com.example.flowtags.data.domain_models.SongForTagging

sealed class SongForTagFetchState {
    data object Idle: SongForTagFetchState()
    data object Fetching: SongForTagFetchState()
    data class SongReady(
        val song: SongForTagging
    ): SongForTagFetchState()
    data object NoMoreSongs: SongForTagFetchState()
    data object Error: SongForTagFetchState()
}