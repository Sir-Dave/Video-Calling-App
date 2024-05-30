package com.sirdave.videostreamapp.connect

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sirdave.videostreamapp.ui.theme.VideoStreamAppTheme

@Composable
fun ConnectScreen(
    state: ConnectState,
    onAction: (ConnectAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(
            text = "Choose a name",
            fontSize = 18.sp
        )

        Spacer(modifier = modifier.height(16.dp))
        TextField(
            value = state.name,
            onValueChange = { onAction(ConnectAction.OnNameChanged(it)) },
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Name")
            }
        )
        Spacer(modifier = modifier.height(16.dp))
        Button(
            modifier = modifier.align(Alignment.End),
            onClick = { onAction(ConnectAction.OnConnectButtonClick) }
        ) {
            Text(text = "Connect")
        }
        Spacer(modifier = modifier.height(16.dp))
        if (state.errorMessage != null){
            Text(
                text = state.errorMessage,
                color = MaterialTheme.colorScheme.error
            )

        }

    }
}



@Preview(showBackground = true)
@Composable
fun ConnectScreenPreview() {
    VideoStreamAppTheme {
        ConnectScreen(
            state = ConnectState(
                errorMessage = "Invalid name"
            ),
            onAction = {}
        )
    }
}