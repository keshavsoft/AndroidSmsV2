package com.example.smsapp.ui.common.conversation.conversationScreen

import android.provider.Telephony
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.ui.common.conversation.ChatBubbleIncoming
import com.example.smsapp.ui.common.conversation.ChatBubbleOutgoing

@Composable
fun MessageList(
    messages: List<SmsMessage>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = listState,
        reverseLayout = true,
        modifier = modifier
    ) {
        items(messages) { sms ->
            MessageItem(sms)
        }
    }
}

@Composable
fun MessageItem(sms: SmsMessage) {
    if (sms.type == Telephony.Sms.MESSAGE_TYPE_INBOX)
        ChatBubbleIncoming(sms.body)
    else
        ChatBubbleOutgoing(sms.body)
}