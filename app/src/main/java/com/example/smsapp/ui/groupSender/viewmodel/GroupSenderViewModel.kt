package com.example.smsapp.ui.groupSender.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.data.SmsReaderRepository
import kotlinx.coroutines.flow.MutableStateFlow

open class GroupSenderViewModel : ViewModel() {
    val grouped = MutableStateFlow<Map<String, Int>>(emptyMap())
    private val _selectedSender = MutableStateFlow<String?>(null)
    val selectedSender = _selectedSender

    fun load(context: Context) {
        val msgs = SmsReaderRepository(context).getInboxMessages()
        grouped.value = msgs.groupBy { it.address }
            .mapValues { it.value.size }
    }

    fun onSenderClick(sender: String) {
        _selectedSender.value = sender
    }

    val messagesForSender = MutableStateFlow<List<SmsMessage>>(emptyList())

    fun loadMessagesForSender(context: Context, sender: String) {
        val msgs = SmsReaderRepository(context).getInboxMessages()
        messagesForSender.value = msgs.filter { it.address == sender }
    }
}