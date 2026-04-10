package com.example.smsapp.ui.common.conversation

import android.provider.Telephony
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvThreadAllLeftRight(
    address: String,
    title: String = "",
    openDrawer: () -> Unit
) {
    val context = LocalContext.current
    var messages by remember { mutableStateOf<List<SmsMessage>>(emptyList()) }
    var titleText by remember { mutableStateOf(title) }

    LaunchedEffect(address) {
        val normalizedTarget = normalizeAddress(address)

        val repo = SmsReaderRepository(context)

        messages = repo.getAllMessages()
            .filter { normalizeAddress(it.address) == normalizedTarget }
            .sortedBy { it.dateLong }

        titleText = "${titleText} (${messages.size})"
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

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(messages) { sms ->
                if (sms.type == Telephony.Sms.MESSAGE_TYPE_INBOX)
                    ChatBubbleIncoming(sms.body)
                else
                    ChatBubbleOutgoing(sms.body)
            }
        }
    }
}
