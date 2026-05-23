package com.example.flowtags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.repo.RepoFlowTags
import com.example.flowtags.ui.models.HomeFragmentsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlowTagsViewModel(
    private val repoFlowTags: RepoFlowTags,
): ViewModel() {
    private val _homeFragmentsState = MutableStateFlow<HomeFragmentsState>(
        HomeFragmentsState.TagList(
            emptyList()
        )
    )
    val homeFragmentsState: StateFlow<HomeFragmentsState>
        get() = _homeFragmentsState

    private val _songTags = mutableListOf<SongTag>()

    init {
        viewModelScope.launch {
            val maybeSongTags = repoFlowTags.getSongTags()
            maybeSongTags?.let {
                _songTags.addAll(it)
                _homeFragmentsState.value = HomeFragmentsState.TagList(_songTags)
            }
        }
    }

    fun onSongTagClick(songTag: SongTag) {
        _homeFragmentsState.value = HomeFragmentsState.TaggingSongs(
            currentTag = songTag,
        )
    }

    fun onNavBack() {
        _homeFragmentsState.value = HomeFragmentsState.TagList(
            _songTags
        )
    }
}