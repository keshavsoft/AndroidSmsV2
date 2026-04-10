package com.example.smsapp.data

import android.content.Context
import android.provider.Telephony
import java.text.SimpleDateFormat
import java.util.*

class SmsReaderRepository(private val context: Context) {

    fun getInboxMessages(): List<SmsMessage> {

        val smsList = mutableListOf<SmsMessage>()

        val cursor = context.contentResolver.query(
            Telephony.Sms.Inbox.CONTENT_URI,
            null,
            null,
            null,
            Telephony.Sms.DEFAULT_SORT_ORDER
        )

        cursor?.use {

            val addressIndex = it.getColumnIndex(Telephony.Sms.ADDRESS)
            val bodyIndex = it.getColumnIndex(Telephony.Sms.BODY)
            val dateIndex = it.getColumnIndex(Telephony.Sms.DATE)
            val typeIndex = it.getColumnIndex(Telephony.Sms.TYPE)

            while (it.moveToNext()) {

                val address = it.getString(addressIndex)
                val body = it.getString(bodyIndex)
                val dateMillis = it.getLong(dateIndex)
                val type = it.getInt(typeIndex)

                val formattedDate = SimpleDateFormat(
                    "dd MMM yyyy, hh:mm a",
                    Locale.getDefault()
                ).format(Date(dateMillis))

                smsList.add(
                    SmsMessage(
                        address = address,
                        body = body,
                        date = formattedDate,
                        type
                    )
                )
            }
        }

        return smsList
    }

    fun getGroupedMessages() = getInboxMessages().groupBy { it.address }

    fun getOutgoingMessages(): List<SmsMessage> {

        val smsList = mutableListOf<SmsMessage>()

        val cursor = context.contentResolver.query(
            Telephony.Sms.Sent.CONTENT_URI,
            null,
            null,
            null,
            Telephony.Sms.DEFAULT_SORT_ORDER
        )

        cursor?.use {
            val addressIndex = it.getColumnIndex(Telephony.Sms.ADDRESS)
            val bodyIndex = it.getColumnIndex(Telephony.Sms.BODY)
            val dateIndex = it.getColumnIndex(Telephony.Sms.DATE)
            val typeIndex = it.getColumnIndex(Telephony.Sms.TYPE)

            while (it.moveToNext()) {
                val address = it.getString(addressIndex)
                val body = it.getString(bodyIndex)
                val dateMillis = it.getLong(dateIndex)
                val type = it.getInt(typeIndex)

                val formattedDate = SimpleDateFormat(
                    "dd MMM yyyy, hh:mm a",
                    Locale.getDefault()
                ).format(Date(dateMillis))

                smsList.add(
                    SmsMessage(address, body, formattedDate,type)
                )
            }
        }

        return smsList
    }

    fun getIncomingMessages(): List<SmsMessage> {

        val smsList = mutableListOf<SmsMessage>()

        val cursor = context.contentResolver.query(
            Telephony.Sms.Inbox.CONTENT_URI,  // ✅ correct
            null,
            null,
            null,
            Telephony.Sms.DEFAULT_SORT_ORDER
        )

        cursor?.use {
            val addressIndex = it.getColumnIndex(Telephony.Sms.ADDRESS)
            val bodyIndex = it.getColumnIndex(Telephony.Sms.BODY)
            val dateIndex = it.getColumnIndex(Telephony.Sms.DATE)
            val typeIndex = it.getColumnIndex(Telephony.Sms.TYPE)

            while (it.moveToNext()) {
                val address = it.getString(addressIndex)
                val body = it.getString(bodyIndex)
                val dateMillis = it.getLong(dateIndex)
                val type = it.getInt(typeIndex)

                val formattedDate = SimpleDateFormat(
                    "dd MMM yyyy, hh:mm a",
                    Locale.getDefault()
                ).format(Date(dateMillis))

                smsList.add(
                    SmsMessage(address, body, formattedDate, type)
                )
            }
        }

        return smsList
    }

    fun getAllMessages1(): List<SmsMessage> {
        return getIncomingMessages() + getOutgoingMessages()
    }

    fun getAllMessages(): List<SmsMessage> {
        val smsList = mutableListOf<SmsMessage>()

        val cursor = context.contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            null, null, null,
            Telephony.Sms.DEFAULT_SORT_ORDER
        )

        cursor?.use {
            val addressIndex = it.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)
            val bodyIndex = it.getColumnIndexOrThrow(Telephony.Sms.BODY)
            val dateIndex = it.getColumnIndexOrThrow(Telephony.Sms.DATE)
            val typeIndex = it.getColumnIndexOrThrow(Telephony.Sms.TYPE)

            while (it.moveToNext()) {
                val address = it.getString(addressIndex)
                val body = it.getString(bodyIndex)
                val dateMillis = it.getLong(dateIndex)
                val type = it.getInt(typeIndex)

                val formattedDate = SimpleDateFormat(
                    "dd MMM yyyy, hh:mm a",
                    Locale.getDefault()
                ).format(Date(dateMillis))

                smsList.add(SmsMessage(address, body, formattedDate, type))
            }
        }
        return smsList
    }
}