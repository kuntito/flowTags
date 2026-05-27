package com.example.flowtags.ui.models

import com.example.flowtags.data.domain_models.SongTag

sealed class TagListState {
    data object Idle: TagListState()
    data object Fetching: TagListState()
    data class TagsReady(
        val songTags: List<SongTag>
    ): TagListState()
    data object NoTags: TagListState()
    data object Error: TagListState()
}