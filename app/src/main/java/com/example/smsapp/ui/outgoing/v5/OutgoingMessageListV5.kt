package com.example.smsapp.ui.outgoing.v5

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.ui.outgoing.components.formatTimeAgo

@Composable
fun OutgoingMessageListV5(
    messages: List<SmsMessage>,
    onItemClick: (SmsMessage) -> Unit
) {
    LazyColumn {
        items(messages) { sms ->
            OutgoingMessageItemV5(
                sms = sms,
                timeAgo = formatTimeAgo(sms.date),
                onClick = onItemClick
            )
        }
    }
}