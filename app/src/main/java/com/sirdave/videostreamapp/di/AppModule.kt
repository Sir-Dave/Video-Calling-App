package com.sirdave.videostreamapp.di

import com.sirdave.videostreamapp.VideoStreamApp
import com.sirdave.videostreamapp.connect.ConnectViewModel
import com.sirdave.videostreamapp.video.VideoCallViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factory {
        val app = androidContext().applicationContext as VideoStreamApp
        app.client
    }

    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)
}