package com.example.smsapp.ui.groupSender.common

import com.example.smsapp.ui.groupSender.model.Message

fun groupBySender(messages: List<Message>) =
    messages.groupBy { it.address }.mapValues { it.value.size }