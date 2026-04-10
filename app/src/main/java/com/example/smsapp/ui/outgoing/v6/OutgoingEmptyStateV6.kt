package com.example.smsapp.ui.outgoing.v6

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MarkChatRead
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OutgoingEmptyStateV6() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Icons.Outlined.MarkChatRead,
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "No sent messages",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Nothing sent in this time range",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}