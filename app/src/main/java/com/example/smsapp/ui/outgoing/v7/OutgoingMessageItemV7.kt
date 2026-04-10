package com.example.smsapp.ui.outgoing.v7

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smsapp.data.SmsMessage
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send

@Composable
fun OutgoingMessageItemV7(
    sms: SmsMessage,
    timeAgo: String,
    onClick: (SmsMessage) -> Unit
)
{
    Row(
        modifier = Modifier.padding(14.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f)) {

            Text(text = sms.address, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text(text = sms.body, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
            Text(text = timeAgo, style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        IconButton(onClick = { onClick(sms) }) {
            Icon(Icons.Default.Send, contentDescription = "Open")
        }
    }
}