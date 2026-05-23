package com.example.flowtags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    init {
        viewModelScope.launch {
            val maybeSongTags = repoFlowTags.getSongTags()
            maybeSongTags?.let {
                _homeFragmentsState.value = HomeFragmentsState.TagList(it)
            }
        }
    }
}