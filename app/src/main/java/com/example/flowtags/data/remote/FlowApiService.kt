package com.example.flowtags.data.remote

import com.example.flowtags.data.remote.helper_fns.ApiCallInfo
import com.example.flowtags.data.remote.helper_fns.safeApiCall
import com.example.flowtags.data.remote.models.AddNotTagBody
import com.example.flowtags.data.remote.models.AddNotTagSongResponse
import com.example.flowtags.data.remote.models.AddTagBody
import com.example.flowtags.data.remote.models.AddTagToSongResponse
import com.example.flowtags.data.remote.models.FetchSongTagsResponse
import com.example.flowtags.data.remote.models.GetSongsForTaggingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface FlowApiService {
    @GET("api/flow/song-tag-types")
    suspend fun fetchSongTags(): FetchSongTagsResponse

    @POST("api/flow/song/{songId}/tag")
    suspend fun addTagToSong(
        @Path("songId") songId: Int,
        @Body body: AddTagBody
    ): AddTagToSongResponse

    @POST("api/flow/song/{songId}/notTag")
    suspend fun addNotTagToSong(
        @Path("songId") songId: Int,
        @Body body: AddNotTagBody
    ): AddNotTagSongResponse

    @GET("api/flow/songsForTagging/{tagId}")
    suspend fun getSongsForTagging(
        @Path("tagId") tagId: Int
    ): GetSongsForTaggingResponse
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