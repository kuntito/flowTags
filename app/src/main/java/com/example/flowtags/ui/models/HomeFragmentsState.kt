package com.example.flowtags.ui.models

import com.example.flowtags.data.domain_models.SongTag

sealed class HomeFragmentsState {
    data class TagList(
        val tags: List<SongTag>
    ): HomeFragmentsState()

    data object TaggingSongs: HomeFragmentsState()
}