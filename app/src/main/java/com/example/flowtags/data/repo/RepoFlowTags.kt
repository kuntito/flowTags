package com.example.flowtags.data.repo

import com.example.flowtags.data.domain_models.SongForTagging
import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.remote.FlowApiDataSource
import com.example.flowtags.data.remote.models.toSongForTagging
import com.example.flowtags.data.remote.models.toSongTag

class RepoFlowTags(
    private val flowDs: FlowApiDataSource,
) {
    suspend fun getSongTags(): List<SongTag>? {
        val response = flowDs.safeFetchSongTags()
        return response?.songTagTypes?.map {
            it.toSongTag()
        }
    }

    suspend fun getSongsForTagging(tagId: Int): List<SongForTagging>? {
        val response = flowDs.safeFetchSongsForTagging(tagId)
        return response?.songsForTagging?.map {
            it.toSongForTagging()
        }
    }

    suspend fun addTagToSong(songId: Int, tagId: Int): Boolean {
        val response = flowDs.safeAddTagToSong(songId, tagId)
        return response?.success == true
    }

    suspend fun addNotTagToSong(songId: Int, tagId: Int): Boolean {
        val response = flowDs.safeAddNotTagToSong(songId, tagId)
        return response?.success == true
    }
}