package com.example.flowtags

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowtags.data.domain_models.SongForTagging
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.repo.RepoFlowTags
import com.example.flowtags.helper_classes.SongForTagFetchManager
import com.example.flowtags.ui.models.HomeFragmentsState
import com.example.flowtags.ui.models.TagListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlowTagsViewModel(
    private val repoFlowTags: RepoFlowTags,
): ViewModel() {
    private val _tagListState = MutableStateFlow<TagListState>(
        TagListState.Idle
    )
    val tagListState = _tagListState.asStateFlow()


    private val _homeFragmentsState = MutableStateFlow<HomeFragmentsState>(
        HomeFragmentsState.TagList(
            tagListState = _tagListState
        )
    )
    val homeFragmentsState: StateFlow<HomeFragmentsState>
        get() = _homeFragmentsState

    private val _songTags = mutableListOf<SongTag>()

    var songForTagFetchManager: SongForTagFetchManager? = null

    init {
        fetchTags()
    }

    fun resolveTagListState(
        songTags: List<SongTag>
    ): TagListState {
        return if (songTags.isEmpty()) {
            TagListState.NoTags
        } else {
            TagListState.TagsReady(
                songTags = songTags
            )
        }
    }

    var fetchTagsJob: Job? = null
    fun fetchTags() {
        if (fetchTagsJob?.isActive == true) return
        fetchTagsJob = viewModelScope.launch {
            _tagListState.value = TagListState.Fetching
            val maybeSongTags = repoFlowTags.getSongTags()
            if (maybeSongTags == null) {
                _tagListState.value = TagListState.Error
            } else {
                _songTags.addAll(maybeSongTags)
                _tagListState.value = resolveTagListState(_songTags)

                _homeFragmentsState.value = HomeFragmentsState.TagList(
                    tagListState = _tagListState
                )
            }
        }
    }

    fun refetchTags() {
        fetchTags()
    }

    fun onSongTagClick(songTag: SongTag) {
        val initializedManager = SongForTagFetchManager(
            fetchSongs = { repoFlowTags.getSongsForTagging(songTag.tagId) },
            coroutineScope = viewModelScope,
        )
        songForTagFetchManager = initializedManager

        // used this to Android Studio doesn't complain `songForTagManager` is nullable
        _homeFragmentsState.value = HomeFragmentsState.TaggingSongs(
            currentTag = songTag,
            songForTagFetchState = initializedManager.songForTagFetchState
        )
    }

    fun onNavBack() {
        _tagListState.value = resolveTagListState(_songTags)
        _homeFragmentsState.value = HomeFragmentsState.TagList(
            tagListState = _tagListState
        )
    }


    fun addTagToSong(
        songForTagging: SongForTagging,
        tag: SongTag,
    ) {
        // TODO add tag to song
        songForTagFetchManager?.emitNextSong()
        Log.d(flowTagDebugTag, "swiped right")
    }

    fun addNotTagToSong(
        songForTagging: SongForTagging,
        tag: SongTag,
    ) {
        // TODO add tag to song
        songForTagFetchManager?.emitNextSong()
    }
}