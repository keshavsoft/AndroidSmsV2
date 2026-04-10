package com.example.smsapp.ui.incoming.logic

import com.example.smsapp.data.SmsMessage
import com.example.smsapp.ui.incoming.model.IncomingConversationV1
import com.example.smsapp.utils.normalizeAddress

fun groupBySender(
    messages: List<SmsMessage>,
    contactsMap: Map<String, String>
): List<IncomingConversationV1> {
    val grouped = messages.groupBy { normalizeAddress(it.address) }

    return grouped.map { (address, msgs) ->
        val latest = msgs.maxByOrNull { it.dateLong }!!

        IncomingConversationV1(
            address = contactsMap[address] ?: address,
            lastMessage = latest.body,
            lastTimestamp = latest.dateLong,
            count = msgs.size,
            messages = msgs.sortedByDescending { it.date.toLongOrNull() ?: 0L },
            phoneNumber = address
        )
    }
        .sortedByDescending { it.lastTimestamp }
}