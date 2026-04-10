package com.example.smsapp.ui.common.conversation.conversationScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.data.SmsSenderRepository
import com.example.smsapp.ui.common.conversation.conversationScreen.ui.ConversationUIV1
import com.example.smsapp.ui.common.conversation.conversationScreen.ui.ConversationUIV2
import com.example.smsapp.ui.common.conversation.conversationScreen.ui.ConversationUIV3

@Composable
fun ConversationScreen(
    messages: List<SmsMessage>,
    title: String,
    openDrawer: () -> Unit
) {
    val state = rememberConvState(messages, title)

    HandleConversationEffects(state)
    val ui = ConversationUIV3  // or V2

    val phone = messages.firstOrNull()?.address ?: return
    val smsRepo = remember { SmsSenderRepository() }

    val onSend: (String) -> Unit = { message ->
        smsRepo.sendSms(phone, message)
    }

    ui.Render(
        state = state,
        messages = messages,
        onSendClick = onSend,
        openDrawer = openDrawer
    )
}