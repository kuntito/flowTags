package com.example.flowtags

import androidx.lifecycle.ViewModel
import com.example.flowtags.ui.models.HomeFragmentsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlowTagsViewModel(

): ViewModel() {
    private val _homeFragmentsState = MutableStateFlow<HomeFragmentsState>(
        HomeFragmentsState.TagList(
            emptyList()
        )
    )
    val homeFragmentsState: StateFlow<HomeFragmentsState>
        get() = _homeFragmentsState
}