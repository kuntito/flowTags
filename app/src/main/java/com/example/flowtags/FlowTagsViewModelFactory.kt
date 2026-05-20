package com.example.flowtags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FlowTagsViewModelFactory(

): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(
        modelClass: Class<T>
    ): T {
        return FlowTagsViewModel() as T
    }
}