package com.example.smsapp.ui.groupSender.v2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
fun GroupSenderScreenV2(
    openDrawer: () -> Unit,
    inHeadLabel: String = "Group Sender V2",
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

        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data.entries.toList()) { (sender, count) ->
                Text("$sender : $count")
            }
        }
    }
}