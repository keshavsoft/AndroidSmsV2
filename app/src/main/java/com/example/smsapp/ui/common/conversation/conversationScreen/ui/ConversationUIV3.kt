package com.example.smsapp.ui.common.conversation.conversationScreen.ui

import android.provider.Telephony
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smsapp.data.SmsMessage

import com.example.smsapp.ui.common.conversation.conversationScreen.ConvState
import com.example.smsapp.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
object ConversationUIV2 {
    @Composable
    fun Render(
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
}

@Composable
private fun ConversationTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    AppTopBar(
        title = title,
        showBack = true,
        onBackClick = onBackClick
    )
}


@Composable
private fun MessageList(
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
private fun MessageItem(sms: SmsMessage) {
    val isIncoming = sms.type == Telephony.Sms.MESSAGE_TYPE_INBOX

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isIncoming) Arrangement.Start else Arrangement.End
    ) {
        if (isIncoming) {
            ChatBubbleIncoming(sms.body)
        } else {
            ChatBubbleOutgoing(sms.body)
        }
    }
}

@Composable
private fun MessageInput(
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

@Composable
private fun ChatBubbleIncoming(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = message,
            modifier = Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp,
                    bottomEnd = 25.dp,
                    bottomStart = 0.dp   // sharp
                ))
                .padding(horizontal = 14.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun ChatBubbleOutgoing(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = message,
            modifier = Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 25.dp   // sharp
                ))
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}