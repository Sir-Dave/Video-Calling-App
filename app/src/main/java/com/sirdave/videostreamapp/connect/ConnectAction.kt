package com.sirdave.videostreamapp.connect

sealed interface ConnectAction {
    data class OnNameChanged(val name: String): ConnectAction
    object OnConnectButtonClick: ConnectAction
}