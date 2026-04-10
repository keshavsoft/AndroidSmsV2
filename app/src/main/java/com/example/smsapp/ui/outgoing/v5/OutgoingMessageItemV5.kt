package com.example.smsapp.ui.outgoing.v5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smsapp.data.SmsMessage

@Composable
fun OutgoingMessageItemV5(
    sms: SmsMessage,
    timeAgo: String,
    onClick: (SmsMessage) -> Unit
) {
    Card(
        onClick = { onClick(sms) },
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            Text(
                text = sms.address,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = sms.body,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = timeAgo,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}