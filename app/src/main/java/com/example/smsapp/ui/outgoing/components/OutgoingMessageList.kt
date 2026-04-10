package com.example.smsapp.ui.outgoing.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.smsapp.data.SmsMessage

@Composable
fun OutgoingMessageList(
    messages: List<SmsMessage>,
    onItemClick: (SmsMessage) -> Unit
) {
    LazyColumn {
        items(messages) { sms ->
            OutgoingMessageItem(
                sms = sms,
                onClick = onItemClick
            )
        }
    }
}