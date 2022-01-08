package com.geektech.youtubeapi.ui.playlist

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.core.network.result.Status
import com.geektech.youtubeapi.utils.`object`.Constant.KEY
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.databinding.ActivityPlaylistBinding
import com.geektech.youtubeapi.databinding.ItemNetworkBinding
import com.geektech.youtubeapi.extentions.visible
import com.geektech.youtubeapi.data.remote.model.Items
import com.geektech.youtubeapi.extentions.showToast
import com.geektech.youtubeapi.ui.playlistDetail.PlaylistDetailActivity

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    private lateinit var adapter: PlaylistAdapter
    private lateinit var bindingNetwork: ItemNetworkBinding


    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this)[PlaylistViewModel::class.java]
        bindingNetwork = ItemNetworkBinding.inflate(layoutInflater)
        checkInet()
    }

    override fun initListener() {
        super.initListener()
        bindingNetwork.btnNetwork.setOnClickListener {
            checkInet()
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlaylist().observe(this) {
            when(it.status){
                Status.LOADING->{viewModel.loading.postValue(true)}
                Status.SUCCESS->{
                    viewModel.loading.postValue(false)
                    adapter = PlaylistAdapter(it.data?.items as ArrayList<Items>)
                    binding.recyclerView.adapter = adapter
                    adapter.setOnItemClickListener(object : PlaylistAdapter.OnItemClickListener {
                        override fun onItemClick(id: String) {
                            Intent(this@PlaylistActivity, PlaylistDetailActivity::class.java).apply {
                                putExtra(KEY, id)
                                startActivity(this)
                            }
                        }
                    })
                }
                Status.ERROR->{
                    viewModel.loading.postValue(false)
                    showToast(it.message.toString())
                }
            }
            adapter = PlaylistAdapter(it.data?.items as ArrayList<Items>)
            binding.recyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : PlaylistAdapter.OnItemClickListener {
                override fun onItemClick(id: String) {
                    Intent(this@PlaylistActivity, PlaylistDetailActivity::class.java).apply {
                        putExtra(KEY, id)
                        startActivity(this)
                    }
                }
            })
        }
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    override fun checkInet() {
        super.checkInet()
        if (isInternetConnected(this)) {
            binding.leanerL.visible = false
            initViewModel()
        } else
            binding.leanerL.visible = true
    }
}