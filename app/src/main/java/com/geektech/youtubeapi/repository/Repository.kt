package com.geektech.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.BuildConfig.API_KEY
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.utils.`object`.Constant
import com.geektech.youtubeapi.data.remote.model.PlayList
import kotlinx.coroutines.Dispatchers

class Repository {

    fun createPlayList(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response =
            App().youtubeaApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, API_KEY, 20)
        if (response.isSuccessful && response.body() != null) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), response.body(), response.code()))
        }
    }

    fun createPlayItem(videoPlaylistId: String): LiveData<Resource<PlayList>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val responce =
                App().youtubeaApi.getPlaylistItems(Constant.PART, videoPlaylistId, API_KEY, 20)
            if (responce.isSuccessful && responce.body() != null) {
                emit(Resource.success(responce.body()))
            } else {
                emit(Resource.error(responce.message(), responce.body(), responce.code()))
            }
        }
}