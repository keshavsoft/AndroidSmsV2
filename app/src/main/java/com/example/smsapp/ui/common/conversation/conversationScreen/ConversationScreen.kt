package com.example.smsapp.ui.common.conversation.conversationScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    val onSend: (String) -> Unit = { message ->
        smsRepo.sendSms(phone, message)
        Toast.makeText(context, "$message : Sent", Toast.LENGTH_SHORT).show()
        state.clearInput()
    }

    ui.Render(
        state = state,
        messages = messages,
        onSendClick = onSend,
        openDrawer = openDrawer
    )
}