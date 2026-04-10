package com.example.smsapp.ui.common.conversation.conversationScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderConversationUI(
    state: ConvState,
    messages: List<SmsMessage>,
    onSendClick: (String) -> Unit,
    openDrawer: () -> Unit
) {
    Scaffold(
        topBar = {
            ConversationTopBar(
                title = state.title,
                onBackClick = openDrawer
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            MessageList(
                messages = messages,
                listState = state.listState,
                modifier = Modifier.weight(1f)
            )

            MessageInput(
                inputText = state.inputText,
                onInputChange = state.onInputChange,
                onSendClick = {
                    onSendClick(state.inputText)
                    state.clearInput()
                },
                focusRequester = state.focusRequester
            )
        }
    }
}

@Composable
fun ConversationTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    AppTopBar(
        title = title,
        showBack = true,
        onBackClick = onBackClick
    )
}