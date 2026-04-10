package com.example.smsapp.ui.common.conversation

import android.provider.Telephony
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.ui.components.AppTopBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvScreen(
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
                listState = state.listState
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
fun rememberConvState(
    messages: List<SmsMessage>,
    title: String
): ConvState {
    val listState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }

    var inputText by remember { mutableStateOf("") }
    var titleText by remember { mutableStateOf(buildTitle(title, messages.size)) }

    LaunchedEffect(messages.size) { titleText = buildTitle(title, messages.size) }

    return ConvState(
        listState,
        focusRequester,
        inputText,
        titleText,
        onInputChange = { inputText = it },
        clearInput = { inputText = "" },
        updateTitle = { titleText = it }
    )
}
data class ConvState(
    val listState: LazyListState,
    val focusRequester: FocusRequester,
    val inputText: String,
    val title: String,
    val onInputChange: (String) -> Unit,
    val clearInput: () -> Unit,
    val updateTitle: (String) -> Unit
)
@Composable
fun HandleConversationEffects(state: ConvState) {

    LaunchedEffect(state.title) {
        // if needed
    }

    LaunchedEffect(Unit) {
        state.focusRequester.requestFocus()
    }
}

@Composable
fun MessageList(
    messages: List<SmsMessage>,
    listState: LazyListState,
    modifier: Modifier = Modifier
)
{
    LazyColumn(
        state = listState,
        reverseLayout = true,
        modifier = modifier
            .fillMaxWidth()
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
private fun buildTitle(title: String, count: Int): String {
    return "$title ($count)"
}