package com.example.networkandthreads

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class DownloadViewModel : ViewModel() {

    val downloadedText: MutableLiveData<String> = MutableLiveData()

    fun downloadFile() {
        viewModelScope.launch {
            val result = fetchFileContent("https://users.metropolia.fi/~jarkkov/koe.txt")
            downloadedText.value = result
        }
    }

    private suspend fun fetchFileContent(url: String): String {
        return withContext(Dispatchers.IO) {
            try {
                URL(url).readText()
            } catch (e: Exception) {
                "Error downloading: ${e.localizedMessage}"
            }
        }
    }
}
