package com.example.smsapp.ui.outgoing.v2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smsapp.ui.components.AppTopBar
import com.example.smsapp.viewmodel.InboxViewModel
import com.example.smsapp.viewmodel.TimeGroup

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutgoingScreenV2(
    viewModel: InboxViewModel = viewModel(),
    openDrawer: () -> Unit,
    inHeadLabel: String = "Outgoing V2"
) {
    val context = LocalContext.current
    val grouped by viewModel.grouped.collectAsState()
    var tab by remember { mutableStateOf(TimeGroup.TODAY) }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) viewModel.loadOutgoingMessages(context)
        }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.loadOutgoingMessages(context)
        } else {
            permissionLauncher.launch(Manifest.permission.READ_SMS)
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

            TabRow(selectedTabIndex = tab.ordinal) {
                TimeGroup.values().forEach {
                    Tab(
                        selected = tab == it,
                        onClick = { tab = it },
                        text = { Text(it.name) }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(grouped[tab] ?: emptyList()) { sms ->

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {

                            Text(
                                text = sms.address,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = sms.body,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = sms.date,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}
