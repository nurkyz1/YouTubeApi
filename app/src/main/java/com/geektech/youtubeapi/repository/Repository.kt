package com.geektech.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.BuildConfig
import com.geektech.youtubeapi.BuildConfig.API_KEY
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.utils.`object`.Constant
import com.geektech.youtubeapi.data.remote.model.PlayList
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

//    fun createPlayList():LiveData<Resource<PlayList>> = liveData(Dispatchers.IO){
//        emit(Resource.loading())
//
//        val response = App().youtubeaApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY,20)
//
//        if (response.isSuccessful&&response.body()!=null) {
//            emit(Resource.success(response.body()))
//        }else {
//            emit(Resource.error(response.message(), response.body(), response.code()))
//        }
//    }
    fun createPlayList(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response =
            App().youtubeaApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 20)
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
                App().youtubeaApi.getPlaylistItems(Constant.PART, videoPlaylistId, BuildConfig.API_KEY, 20)
            if (responce.isSuccessful && responce.body() != null) {
                emit(Resource.success(responce.body()))
            } else {
                emit(Resource.error(responce.message(), responce.body(), responce.code()))
            }
        }


//     fun createPlaylist(): LiveData<PlayList> {
//        val data = MutableLiveData<PlayList>()
//
//        App().youtubeaApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY,20)
//            .enqueue(object : Callback<PlayList> {
//                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
//                    if (response.isSuccessful && response.body() != null) {
//                        data.value = response.body()
//                    }
//                }
//
//                override fun onFailure(call: Call<PlayList>, t: Throwable) {
//                    print(t.stackTrace)
//                }
//
//            })
//        return data
    //}
//fun getPlaylistItems(id: String): LiveData<PlayList> {
//    val data = MutableLiveData<PlayList>()
//
//    App().youtubeaApi.getPlayListItems(Constant.PART, id,20, API_KEY).enqueue(object : Callback<PlayList>{
//        override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
//            if (response.isSuccessful && response.body() != null) {
//                data.value = response.body()
//            }
//        }
//        override fun onFailure(call: Call<PlayList>, t: Throwable) {
//            print(t.stackTrace)
//        }
//
//    })
//    return data
//}
}