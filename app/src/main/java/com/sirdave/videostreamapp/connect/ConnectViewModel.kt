package com.sirdave.videostreamapp.connect

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.sirdave.videostreamapp.VideoStreamApp

class ConnectViewModel(
    private val app: Application
): AndroidViewModel(app) {

    var state by mutableStateOf(ConnectState())
        private set

    fun onAction(action: ConnectAction){
        when (action){
            is ConnectAction.OnNameChanged -> {
                state = state.copy(name = action.name)
            }

            is ConnectAction.OnConnectButtonClick -> {
                connectToRoom()
            }
        }
    }

    private fun connectToRoom(){
        state = state.copy(errorMessage = null)

        if (state.name.isBlank()){
            state = state.copy(errorMessage = "Username cannot be blank")
            return
        }

        (app as VideoStreamApp).initVideoClient(state.name)
        state = state.copy(isConnected = true)
    }
}