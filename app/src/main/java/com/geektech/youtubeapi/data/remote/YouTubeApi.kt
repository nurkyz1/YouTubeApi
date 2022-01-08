package com.geektech.youtubeapi.data.remote

import com.geektech.youtubeapi.data.remote.model.PlayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {
    @GET("playlists")
   suspend fun getPlaylists(
        @Query("part")part: String,
        @Query("channelId")channelId: String,
        @Query("key")apiKey: String,
        @Query("maxResults")maxResult: Int

    ): Response<PlayList>
    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("key") apikey: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlayList>
}