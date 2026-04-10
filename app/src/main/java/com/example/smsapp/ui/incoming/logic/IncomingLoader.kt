package com.example.smsapp.ui.incoming.logic

import android.content.Context
import android.provider.Telephony
import com.example.smsapp.data.SmsMessage

fun loadIncomingSms(context: Context): List<SmsMessage> {

    val list = mutableListOf<SmsMessage>()

    val cursor = context.contentResolver.query(
        Telephony.Sms.Inbox.CONTENT_URI,
        null, null, null, "date DESC"
    )

    cursor?.use {
        val addr = it.getColumnIndexOrThrow("address")
        val body = it.getColumnIndexOrThrow("body")
        val date = it.getColumnIndexOrThrow("date")

        while (it.moveToNext()) {
            list.add(
                SmsMessage(
                    address = it.getString(addr) ?: "",
                    body = it.getString(body) ?: "",
                    date = it.getString(date) ?: ""
                )
            )
        }
    }

    return list
}