package com.example.smsapp.ui.common.conversation.conversationScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp

@Composable
fun MessageInput(
    inputText: String,
    onInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
    focusRequester: FocusRequester
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = inputText,
            onValueChange = onInputChange,
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            placeholder = { Text("Type message") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(onClick = onSendClick) {
            Text("Send")
        }
    }
}