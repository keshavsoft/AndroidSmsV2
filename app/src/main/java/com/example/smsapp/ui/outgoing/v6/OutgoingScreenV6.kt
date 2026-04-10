package com.example.smsapp.ui.outgoing.v6

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smsapp.ui.common.SmsPermissionLoader
import com.example.smsapp.ui.components.AppTopBar
import com.example.smsapp.ui.outgoing.components.OutgoingTabs
import com.example.smsapp.viewmodel.InboxViewModel
import com.example.smsapp.viewmodel.SmsViewModel
import com.example.smsapp.viewmodel.TimeGroup
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutgoingScreenV6(
    viewModel: InboxViewModel = viewModel(),
    smsViewModel: SmsViewModel = viewModel(),
    openDrawer: () -> Unit,
    navigateToSend: (String, String) -> Unit,
    inHeadLabel: String = "Outgoing V6"
) {
    val grouped by viewModel.grouped.collectAsState()
    var tab by remember { mutableStateOf(TimeGroup.TODAY) }
    val smsViewModel: SmsViewModel = viewModel()
    var tick by remember { mutableStateOf(0) }
    val context = LocalContext.current

    SmsPermissionLoader(onGranted = { viewModel.loadOutgoingMessages(it) }) {
        // existing Scaffold here
        LaunchedEffect(Unit) {
            while (true) {
                delay(60000)
                tick++
            }
        }

        Scaffold(
            topBar = {
                AppTopBar(
                    title = inHeadLabel,
                    showBack = false,
                    onMenuClick = openDrawer
                )
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                val counts = grouped.mapValues { it.value.size }

                OutgoingTabs(
                    selected = tab,
                    counts = counts,
                    onSelected = { tab = it }
                )

                OutgoingMessageListV6(
                    messages = grouped[tab] ?: emptyList(),
                    tick = tick,
                    onItemClick = { sms ->
                        smsViewModel.prepareResend(sms)
                        navigateToSend(sms.address, sms.body)
                    }
                )

                LaunchedEffect(tick, grouped[tab]) {
                    Toast.makeText(context, "UI Refreshed", Toast.LENGTH_SHORT).show()
                }

                tick

            }
        }
    }
}
