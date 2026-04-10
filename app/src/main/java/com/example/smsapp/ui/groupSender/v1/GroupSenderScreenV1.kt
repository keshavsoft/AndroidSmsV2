package com.example.smsapp.ui.groupSender.v1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smsapp.ui.groupSender.viewmodel.GroupSenderViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun GroupSenderScreenV1(vm: GroupSenderViewModel = viewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.load(context)
    }

    val data by vm.grouped.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = data.entries.toList(),
            key = { it.key }
        ) { (sender, count) ->
            Text("$sender : $count")
        }
    }
}