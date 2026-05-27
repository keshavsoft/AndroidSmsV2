package com.example.smsapp.ui.incoming.v11

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.smsapp.ui.components.AppTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomingScreenV11(
    openDrawer: () -> Unit = {},
    navigateToThread: (String, String) -> Unit = { _, _ -> },
    inHeadLabel: String = "All Align V11"
) {

    Scaffold(
        topBar = {
            AppTopBar(
                title = inHeadLabel,
                showBack = false,
                onMenuClick = openDrawer
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Button(
                onClick = {

                    CoroutineScope(Dispatchers.IO).launch {

                        val client = OkHttpClient()

                        val json = """
                            {
                                "name": "KeshavSoft"
                            }
                        """.trimIndent()

                        val body = json.toRequestBody(
                            "application/json".toMediaType()
                        )

                        val request = Request.Builder()
                            .url("https://httpbin.org/post")
                            .post(body)
                            .build()

                        val response = client.newCall(request).execute()

                                Log.d("KSOFT", response.body?.string() ?: "")
                    }
                }
            ) {
                Text("Fetch Post")
            }
        }
    }
}