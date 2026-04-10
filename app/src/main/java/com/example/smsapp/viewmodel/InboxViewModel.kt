package com.example.smsapp.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.smsapp.data.SmsMessage
import com.example.smsapp.data.SmsReaderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class TimeGroup { TODAY, YESTERDAY, THIS_MONTH, OLDER }

class InboxViewModel : ViewModel() {
    private var repository: SmsReaderRepository? = null

    private val _messages = MutableStateFlow<List<SmsMessage>>(emptyList())
    val messages: StateFlow<List<SmsMessage>> = _messages

    private val _grouped = MutableStateFlow<Map<TimeGroup, List<SmsMessage>>>(emptyMap())
    val grouped: StateFlow<Map<TimeGroup, List<SmsMessage>>> = _grouped

    fun loadMessages(context: Context) {
        if (repository == null) {
            repository = SmsReaderRepository(context.applicationContext)
        }
        _messages.value = repository!!.getInboxMessages()
    }

    fun getGroupedMessages(context: Context): Map<String, List<SmsMessage>> {
        if (repository == null) {
            repository = SmsReaderRepository(context.applicationContext)
        }
        return repository!!.getGroupedMessages()
    }

    fun loadIncomingMessages(context: Context) {
        if (repository == null) {
            repository = SmsReaderRepository(context.applicationContext)
        }
        _messages.value = repository!!.getIncomingMessages()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadOutgoingMessages(context: Context) {
        if (repository == null) {
            repository = SmsReaderRepository(context.applicationContext)
        }
        _messages.value = repository!!.getOutgoingMessages()

        val list = repository!!.getOutgoingMessages()
        _messages.value = list
        _grouped.value = groupByTime(list)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun groupByTime(list: List<SmsMessage>): Map<TimeGroup, List<SmsMessage>> {
    val today = java.time.LocalDate.now()
    val yesterday = today.minusDays(1)

    return list.groupBy { sms ->
                val formatter = java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", java.util.Locale.getDefault())
        val date = java.time.LocalDateTime.parse(sms.date, formatter).toLocalDate()

        when {
            date == today -> TimeGroup.TODAY
            date == yesterday -> TimeGroup.YESTERDAY
            date.month == today.month -> TimeGroup.THIS_MONTH
            else -> TimeGroup.OLDER
        }
    }
}