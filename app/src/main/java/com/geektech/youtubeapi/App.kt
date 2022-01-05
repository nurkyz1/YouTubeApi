package com.geektech.youtubeapi

import android.app.Application
import com.geektech.youtubeapi.repository.Repository

class App: Application() {
    val repository by lazy { Repository() }

}