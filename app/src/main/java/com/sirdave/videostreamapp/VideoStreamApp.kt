package com.sirdave.videostreamapp

import android.app.Application
import com.sirdave.videostreamapp.di.appModule
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideoStreamApp: Application() {
    private var currentName: String? = null
    var client: StreamVideo? = null

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VideoStreamApp)
            modules(appModule)
        }
    }


    fun initVideoClient(username: String){
        if (client == null || username != currentName){
            StreamVideo.removeClient()
            currentName = username

            client = StreamVideoBuilder(
                context = this,
                apiKey = BuildConfig.STREAM_API_KEY,
                user = User(
                    id = username,
                    name = username,
                    type = UserType.Guest
                )
            ).build()
        }

    }
}