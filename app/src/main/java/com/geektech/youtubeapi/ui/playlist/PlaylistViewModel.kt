package com.geektech.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.BuildConfig.API_KEY
import com.geektech.youtubeapi.`object`.Constant
import com.geektech.youtubeapi.base.BaseViewModel
import com.geektech.youtubeapi.model.PlayList
import com.geektech.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel(){

    private val youtubeaApi = RetrofitClient.create()

    fun getPlaylist():LiveData<PlayList>{
        return  createPlaylist()
    }
    private fun createPlaylist(): LiveData<PlayList> {
        val data = MutableLiveData<PlayList>()

        youtubeaApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, API_KEY,20)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    print(t.stackTrace)
                }

            })
        return data
    }

}