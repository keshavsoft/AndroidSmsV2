package com.example.smsapp.ui.incoming.logic

import com.example.smsapp.data.SmsMessage
import java.util.*

fun groupIncoming(messages: List<SmsMessage>): Map<IncomingTimeGroup, List<SmsMessage>> {

    val now = Calendar.getInstance()
    val today = now.clone() as Calendar
    val yesterday = now.clone() as Calendar
    yesterday.add(Calendar.DAY_OF_YEAR, -1)
    val week = now.clone() as Calendar
    week.add(Calendar.DAY_OF_YEAR, -7)

    return messages.groupBy { sms ->
        val time = sms.date.toLongOrNull() ?: return@groupBy IncomingTimeGroup.OLDER
        val msgCal = Calendar.getInstance().apply { timeInMillis = time }

        when {
            sameDay(msgCal, today) -> IncomingTimeGroup.TODAY
            sameDay(msgCal, yesterday) -> IncomingTimeGroup.YESTERDAY
            msgCal.after(week) -> IncomingTimeGroup.THIS_WEEK
            else -> IncomingTimeGroup.OLDER
        }
    }
}

private fun sameDay(a: Calendar, b: Calendar) =
    a.get(Calendar.YEAR) == b.get(Calendar.YEAR) &&
            a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR)