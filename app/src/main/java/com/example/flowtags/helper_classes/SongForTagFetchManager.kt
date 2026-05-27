package com.example.flowtags.helper_classes

import android.util.Log
import com.example.flowtags.data.domain_models.SongForTagging
import com.example.flowtags.flowTagDebugTag
import com.example.flowtags.ui.models.SongForTagFetchState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SongForTagFetchManager(
    private val fetchSongs: suspend () -> List<SongForTagging>?,
    private val coroutineScope: CoroutineScope,
) {
    private val _songForTagFetchState = MutableStateFlow<SongForTagFetchState>(
        SongForTagFetchState.Idle
    )
    val songForTagFetchState = _songForTagFetchState.asStateFlow()

    private val fetchedSongs = mutableListOf<SongForTagging>()

    fun handleSongFetch() {
        _songForTagFetchState.value = SongForTagFetchState.Fetching
        coroutineScope.launch {
            val maybeSongsForTagging = fetchSongs()
            if (maybeSongsForTagging == null) {
                _songForTagFetchState.value = SongForTagFetchState.Error
            } else {
                if (maybeSongsForTagging.isEmpty()) {
                    _songForTagFetchState.value = SongForTagFetchState.NoMoreSongs
                } else {
                    fetchedSongs.addAll(maybeSongsForTagging)
                    emitNextSong()
                }
            }
        }
    }

    init {
        handleSongFetch()
    }

    fun emitNextSong() {
        Log.d(flowTagDebugTag, "total fetched: ${fetchedSongs.size}")
        if (fetchedSongs.isEmpty()) {
            Log.d(flowTagDebugTag, "fetching more songs")
            handleSongFetch()
        } else {
            val nextSong = fetchedSongs.removeLast()
            Log.d(flowTagDebugTag, "serving: ${nextSong.songTitle}")
            _songForTagFetchState.value = SongForTagFetchState.SongReady(nextSong)
        }
    }
}