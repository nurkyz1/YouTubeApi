package com.geektech.youtubeapi.ui.playlistDetail

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.core.network.result.Status
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.data.remote.model.Items
import com.geektech.youtubeapi.utils.`object`.Constant.KEY
import com.geektech.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.geektech.youtubeapi.databinding.ItemNetworkBinding
import com.geektech.youtubeapi.extentions.showToast
import com.geektech.youtubeapi.extentions.visible
import com.geektech.youtubeapi.ui.video.VideoPlayerActivity

class PlaylistDetailActivity :
    BaseActivity<PlaylistDetailViewModel, ActivityPlaylistDetailBinding>() {

    private lateinit var bindingNetwork: ItemNetworkBinding

    private lateinit var id: String
    private var list = mutableListOf<Items>()
    private lateinit var adapter: PlaylistDetailAdapter


    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this)[PlaylistDetailViewModel::class.java]
        bindingNetwork = ItemNetworkBinding.inflate(layoutInflater)
        checkInet()
        id = intent.getStringExtra("id_key").toString()
        binding.tvDescription.text = intent.getStringExtra("description_key").toString()
        binding.tvTitle.text = intent.getStringExtra("title_key").toString()

    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlaylistsDetail(id).observe(this) {
            when (it.status) {
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    binding.detailVideos.adapter = adapter
                    adapter.setOnClickListener(object : PlaylistDetailAdapter.OnClickListener {
                        override fun onClickListener(items: Items) {
                            Intent(
                                this@PlaylistDetailActivity,
                                VideoPlayerActivity::class.java
                            ).apply {
                                putExtra(KEY, items.id)
                                startActivity(this)
                            }
                        }
                    })
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    showToast("Error")
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()
        bindingNetwork.btnNetwork.setOnClickListener {
            checkInet()
        }
        binding.fab.setOnClickListener {
            Intent(this, VideoPlayerActivity::class.java).apply {
                putExtra(KEY, list[0].id)
                startActivity(this)
            }
        }
        binding.tvBack.setOnClickListener {
            finish()
        }
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