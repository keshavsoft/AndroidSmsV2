package com.example.smsapp.ui.outgoing.v7

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.ui.outgoing.components.formatTimeAgoWithTick

@Composable
fun OutgoingMessageListV7(
    messages: List<SmsMessage>,
    tick: Int,
    onItemClick: (SmsMessage) -> Unit
) {

    if (messages.isEmpty()) {
        OutgoingEmptyStateV7()
        return
    }

    LazyColumn {
        items(messages) { sms ->
            OutgoingMessageItemV7(
                sms = sms,
                timeAgo = formatTimeAgoWithTick(sms.date, tick),
                onClick = onItemClick
            )
        }
    }
}