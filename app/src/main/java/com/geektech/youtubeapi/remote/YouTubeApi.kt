package com.geektech.youtubeapi.remote

import com.geektech.youtubeapi.model.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {
    @GET("playlists")
    fun getPlaylists(
        @Query("part")part: String,
        @Query("channelId")channelId: String,
        @Query("key")apiKey: String,
        @Query("maxResults")maxResult: Int

    ): Call<PlayList>
}