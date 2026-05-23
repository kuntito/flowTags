package com.example.flowtags.data.repo

import com.example.flowtags.data.domain_models.SongTag
import com.example.flowtags.data.remote.FlowApiDataSource
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
}