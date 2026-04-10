package com.example.smsapp.ui.incoming.conversation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.smsapp.ui.incoming.ui.IncomingEmptyState
import com.example.smsapp.ui.incoming.model.IncomingConversationV1

@Composable
fun IncomingConversationList(
    conversations: List<IncomingConversationV1>,
    modifier: Modifier = Modifier,
    onOpenConversation: (IncomingConversationV1) -> Unit
) {
    if (conversations.isEmpty()) {
        IncomingEmptyState()
        return
    }

    LazyColumn(modifier = modifier) {
        items(conversations) { convo ->
            IncomingConversationItem(convo) {
                onOpenConversation(convo)
            }
        }
    }
}