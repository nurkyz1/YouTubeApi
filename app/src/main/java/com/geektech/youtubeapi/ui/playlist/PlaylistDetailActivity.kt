package com.geektech.youtubeapi.ui.playlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.youtubeapi.`object`.Constant.KEY
import com.geektech.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.geektech.youtubeapi.extentions.showToast

class PlaylistDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaylistDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val text = intent.getStringExtra(KEY)
        if (text != null) {
            showToast(text)
        }
    }
}