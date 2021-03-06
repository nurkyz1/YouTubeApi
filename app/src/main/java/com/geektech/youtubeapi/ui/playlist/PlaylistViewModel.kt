package com.geektech.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.model.PlayList

class PlaylistViewModel : BaseViewModel(){

    var loading = MutableLiveData<Boolean>()

    fun getPlaylist(): LiveData<Resource<PlayList>> {
        return App().repository.createPlayList()
    }
}