package com.example.flowtags.ui.models

import androidx.compose.runtime.mutableStateOf
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.domain_models.dummySongTag
import com.example.flowtags.data.domain_models.dummySongTagList
import kotlinx.coroutines.flow.StateFlow

sealed class HomeFragmentsState {
    data class TagList(
        val tagListState: StateFlow<TagListState>,
    ): HomeFragmentsState()

    data class TaggingSongs(
        val currentTag: SongTag,
        val songForTagFetchState: StateFlow<SongForTagFetchState>,
    ): HomeFragmentsState()
}

//val dummyTagListFragmentState = HomeFragmentsState.TagList(
//    tagListState = TagListState.Idle
//)