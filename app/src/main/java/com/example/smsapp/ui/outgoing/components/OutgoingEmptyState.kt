package com.example.smsapp.ui.outgoing.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smsapp.viewmodel.TimeGroup

@Composable
fun OutgoingEmptyState(group: TimeGroup) {

    val message = when(group){
        TimeGroup.TODAY -> "No messages sent today"
        TimeGroup.YESTERDAY -> "No messages yesterday"
        TimeGroup.THIS_MONTH -> "No messages this month"
        TimeGroup.OLDER -> "No older messages"
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "📭",
                style = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}