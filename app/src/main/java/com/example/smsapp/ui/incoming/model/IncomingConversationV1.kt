package com.example.smsapp.ui.incoming.model

import com.example.smsapp.data.SmsMessage

data class IncomingConversationV1(
    val address: String,
    val lastMessage: String,
    val lastTimestamp: Long,
    val count: Int,
    val messages: List<SmsMessage>,
    val phoneNumber: String
)