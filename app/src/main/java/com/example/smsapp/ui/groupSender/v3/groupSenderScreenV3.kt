package com.example.smsapp.ui.groupSender.v3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smsapp.ui.groupSender.viewmodel.GroupSenderViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.smsapp.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupSenderScreenV3(
    openDrawer: () -> Unit,
    inHeadLabel: String = "Group Sender V3",
    vm: GroupSenderViewModel = viewModel()
) {

    val context = LocalContext.current
    val data by vm.grouped.collectAsState()

    LaunchedEffect(Unit) {
        vm.load(context)
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
        val selected by vm.selectedSender.collectAsState()

        if (selected != null) {
            val msgs by vm.messagesForSender.collectAsState()

            LaunchedEffect(selected) {
                vm.loadMessagesForSender(context, selected!!)
            }

            Column {
                Text("Conversation: $selected")

                LazyColumn {
                    items(msgs) { msg ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(msg.body)
                        }
                    }
                }
            }
        } else {
            // 👉 YOUR OLD LazyColumn (sender list) goes here


        val sortedData = data
            .toList()
            .sortedByDescending { it.second }

        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = sortedData,
                key = { it.first }
            ) { item ->

                val sender = item.first
                val count = item.second

                androidx.compose.material3.Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { vm.onSenderClick(sender) }
                ) {
                    androidx.compose.foundation.layout.Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = sender,
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = count.toString(),
                            style = androidx.compose.material3.MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun PreviewGroupSenderScreenV3() {

    val fakeData = mapOf(
        "Amazon" to 25,
        "Bank OTP" to 12,
        "Flipkart" to 8,
        "Jio" to 3
    )

    val fakeVm = object : GroupSenderViewModel() {
        init {
            grouped.value = fakeData
        }
    }

    GroupSenderScreenV3(
        openDrawer = {},
        vm = fakeVm
    )
}