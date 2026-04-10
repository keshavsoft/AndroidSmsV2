package com.example.smsapp.ui.common.conversation.conversationScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HandleConversationEffects(state: ConvState) {

    LaunchedEffect(Unit) {
        state.focusRequester.requestFocus()
    }
}