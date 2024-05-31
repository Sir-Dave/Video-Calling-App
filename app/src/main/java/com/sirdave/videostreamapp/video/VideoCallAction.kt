package com.sirdave.videostreamapp.video

sealed interface VideoCallAction{
    data object DisconnectCall: VideoCallAction
    data object JoinCall: VideoCallAction
}