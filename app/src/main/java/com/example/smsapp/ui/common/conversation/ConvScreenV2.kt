package com.example.smsapp.ui.common.conversation

import android.provider.Telephony
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.data.SmsReaderRepository
import com.example.smsapp.ui.components.AppTopBar
import com.example.smsapp.utils.normalizeAddress
import kotlinx.coroutines.launch
import com.example.smsapp.data.SmsSenderRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvScreenV2(
    address: String,
    title: String = "",
    openDrawer: () -> Unit
) {
    val context = LocalContext.current
    var messages by remember { mutableStateOf<List<SmsMessage>>(emptyList()) }
    var titleText by remember { mutableStateOf(title) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(address) {
        val normalizedTarget = normalizeAddress(address)

        val repo = SmsReaderRepository(context)

        messages = repo.getAllMessages()
            .filter { normalizeAddress(it.address) == normalizedTarget }
            .sortedBy { it.dateLong }

        titleText = "${title.ifBlank { address }} (${messages.size})"
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = titleText,
                showBack = true,
                onBackClick = openDrawer
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                reverseLayout = true,   // ✅ ADD THIS
                        modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {

                items(messages) { sms ->
                    if (sms.type == Telephony.Sms.MESSAGE_TYPE_INBOX)
                        ChatBubbleIncoming(sms.body)
                    else
                        ChatBubbleOutgoing(sms.body)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                var inputText by remember { mutableStateOf("") }

                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type message") }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (inputText.isNotBlank()) {

                            val newMsg = SmsMessage(
                                address = address,
                                body = inputText,
                                date = "",
                                type = Telephony.Sms.MESSAGE_TYPE_SENT,
                                dateLong = System.currentTimeMillis()
                            )

                            messages = messages + newMsg

                            SmsSenderRepository().sendSms(address, inputText)

                            scope.launch {
                                listState.animateScrollToItem(0)
                            }

                            inputText = ""
                        }
                    }
                ) {
                    Text("Send")
                }
            }
        }
        }
    }
