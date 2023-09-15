package com.example.networkandthreads

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.*
import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<DownloadViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent(viewModel)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppContent(viewModel: DownloadViewModel) {
    val downloadedText by viewModel.downloadedText.observeAsState("Downloading...")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Network and Threads") },
                actions = {
                    IconButton(onClick = { viewModel.downloadFile() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        }
    ) {
        Text(text = downloadedText ?: "Downloading...", modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun AppContentPreview() {
    val viewModel = DownloadViewModel()
    AppContent(viewModel)
}
