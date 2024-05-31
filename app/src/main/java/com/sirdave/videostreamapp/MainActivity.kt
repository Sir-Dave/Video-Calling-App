package com.sirdave.videostreamapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sirdave.videostreamapp.connect.ConnectScreen
import com.sirdave.videostreamapp.connect.ConnectViewModel
import com.sirdave.videostreamapp.ui.theme.VideoStreamAppTheme
import com.sirdave.videostreamapp.video.CallState
import com.sirdave.videostreamapp.video.VideoCallScreen
import com.sirdave.videostreamapp.video.VideoCallViewModel
import io.getstream.video.android.compose.theme.VideoTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoStreamAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.ConnectScreen.route,
                            modifier = Modifier.padding(innerPadding)
                        ){
                            composable(Screen.ConnectScreen.route){
                                val viewModel = koinViewModel<ConnectViewModel>()
                                val state = viewModel.state
                                LaunchedEffect(key1 = state.isConnected){
                                    if (state.isConnected){
                                        navController.navigate(Screen.VideoCallScreen.route){
                                            popUpTo(Screen.ConnectScreen.route){
                                                inclusive = true
                                            }
                                        }
                                    }
                                }

                                ConnectScreen(state = state, onAction = viewModel::onAction)
                            }

                            composable(Screen.VideoCallScreen.route){
                                val viewModel = koinViewModel<VideoCallViewModel>()
                                val state = viewModel.state
                                LaunchedEffect(key1 = state.callState){
                                    if (state.callState == CallState.ENDED){
                                        navController.navigate(Screen.ConnectScreen.route){
                                            popUpTo(Screen.VideoCallScreen.route){
                                                inclusive = true
                                            }
                                        }
                                    }
                                }

                                VideoTheme {
                                    VideoCallScreen(state = state, onAction = viewModel::onAction)
                                }
                            }
                        }
                        
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object ConnectScreen : Screen("connect")
    object VideoCallScreen : Screen("video_call")
}