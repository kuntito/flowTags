package com.example.flowtags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowtags.data.repo.RepoFlowTags

class FlowTagsViewModelFactory(
    private val repoFlowTags: RepoFlowTags
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(
        modelClass: Class<T>
    ): T {
        return FlowTagsViewModel(
            repoFlowTags = repoFlowTags,
        ) as T
    }
}