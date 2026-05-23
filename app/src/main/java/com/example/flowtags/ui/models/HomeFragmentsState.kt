package com.example.flowtags.ui.models

import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySongTag
import com.example.flowtags.data.domain_models.dummySongTagList

sealed class HomeFragmentsState {
    data class TagList(
        val tags: List<SongTag>
    ): HomeFragmentsState()

    data class TaggingSongs(
        val currentTag: SongTag
    ): HomeFragmentsState()
}

val dummyTagListFragmentState = HomeFragmentsState.TagList(
    tags = dummySongTagList
)

val dummyTaggingSongsFragmentState = HomeFragmentsState.TaggingSongs(
    currentTag = dummySongTag
)