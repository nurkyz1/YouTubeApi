package com.geektech.youtubeapi.ui.playlistDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.App
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.model.PlayList

class PlaylistDetailViewModel: BaseViewModel() {
    var loading = MutableLiveData<Boolean>()

    fun getPlaylistsDetail(playlistId: String): LiveData<Resource<PlayList>> {
        return App().repository.createPlayItem(playlistId)
    }
}