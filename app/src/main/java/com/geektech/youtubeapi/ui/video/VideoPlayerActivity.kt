package com.geektech.youtubeapi.ui.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.youtubeapi.databinding.ActivityVideoPlayerBinding
import com.geektech.youtubeapi.extentions.showToast
import com.geektech.youtubeapi.utils.`object`.Constant.KEY

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showToast(intent.getStringExtra(KEY).toString())
    }
}