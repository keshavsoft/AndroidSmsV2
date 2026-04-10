package com.example.smsapp.ui.common.conversation.conversationScreen

import androidx.compose.runtime.Composable
import com.example.smsapp.data.SmsMessage

@Composable
fun ConversationScreen(
    messages: List<SmsMessage>,
    title: String,
    onSendClick: (String) -> Unit,
    openDrawer: () -> Unit
) {
    val state = rememberConvState(messages, title)

    HandleConversationEffects(state)

    RenderConversationUI(
        state = state,
        messages = messages,
        onSendClick = onSendClick,
        openDrawer = openDrawer
    )
}