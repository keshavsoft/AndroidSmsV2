package com.example.smsapp.ui.common.conversation.conversationScreen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import com.example.smsapp.data.SmsMessage

@Composable
fun rememberConvState(
    messages: List<SmsMessage>,
    title: String
): ConvState {
    val listState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }

    var inputText by remember { mutableStateOf("") }
    var titleText by remember { mutableStateOf(buildTitle(title, messages.size)) }

    LaunchedEffect(messages.size) {
        titleText = buildTitle(title, messages.size)
    }

    return ConvState(
        listState,
        focusRequester,
        inputText,
        titleText,
        onInputChange = { inputText = it },
        clearInput = { inputText = "" }
    )
}

data class ConvState(
    val listState: LazyListState,
    val focusRequester: FocusRequester,
    val inputText: String,
    val title: String,
    val onInputChange: (String) -> Unit,
    val clearInput: () -> Unit
)

private fun buildTitle(title: String, count: Int): String {
    return "$title ($count)"
}