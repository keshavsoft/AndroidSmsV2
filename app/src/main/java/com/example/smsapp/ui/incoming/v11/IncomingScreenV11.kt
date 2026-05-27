package com.example.smsapp.ui.incoming.v10

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.ui.components.AppTopBar
import com.example.smsapp.ui.incoming.common.IncomingPermission
import com.example.smsapp.ui.incoming.conversation.IncomingConversationList
import com.example.smsapp.contacts.data.loadContacts
import com.example.smsapp.data.SmsReaderRepository
import com.example.smsapp.ui.incoming.logic.groupBySender

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomingScreenV10(
    openDrawer: () -> Unit,
    navigateToThread: (String, String) -> Unit,
    inHeadLabel: String = "All Align V10 "
) {
    val context = LocalContext.current
    var messages by remember { mutableStateOf<List<SmsMessage>>(emptyList()) }
    var contactsMap by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var titleText by remember { mutableStateOf(inHeadLabel) }

    val conversations = remember(messages, contactsMap) {
        groupBySender(messages, contactsMap)
            .sortedByDescending { it.lastTimestamp }
    }
    IncomingPermission(context) {
        val repo = SmsReaderRepository(context)
        messages = repo.getAllMessages()
        titleText = "${inHeadLabel} (${messages.size})"

        contactsMap = loadContacts(context)
    }

    Scaffold(
        topBar = {
            AppTopBar(title = titleText, showBack = false, onMenuClick = openDrawer)
        }
    )
    { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            IncomingConversationList(
                conversations = conversations,
                modifier = Modifier.fillMaxSize(),
                onOpenConversation = { convo ->
                    navigateToThread(convo.phoneNumber, convo.address)
                }
            )
        }
    }
}