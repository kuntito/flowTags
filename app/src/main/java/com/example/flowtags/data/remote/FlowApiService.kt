package com.example.flowtags.data.remote

import com.example.flowtags.data.remote.helper_fns.ApiCallInfo
import com.example.flowtags.data.remote.helper_fns.safeApiCall
import com.example.flowtags.data.remote.models.FetchSongTagsResponse
import retrofit2.http.GET


interface FlowApiService {
    @GET("api/flow/song-tag-types")
    suspend fun fetchSongTags(): FetchSongTagsResponse
}

/**
 * a wrapper 'round the flow api client.
 *
 * ensures call API calls are safe
 * i.e. any errors they throw are caught and logged.
 * they never reach the caller.
 *
 * the caller gets a falsy response.
 */
class FlowApiDataSource(
    private val api: FlowApiService
) {
    suspend fun safeFetchSongTags() = safeApiCall(
        ApiCallInfo(
            "`fetchSongTags`, returns all song tags from API",
            fn = {
                api.fetchSongTags()
            }
        )
    )
}