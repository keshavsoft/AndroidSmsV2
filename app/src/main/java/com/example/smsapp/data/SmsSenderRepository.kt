package com.example.smsapp.data

import android.telephony.SmsManager

class SmsSenderRepository {

    fun sendSms(phone: String, message: String): Result<String> {
        return try {
            val smsManager = SmsManager.getDefault()

            val parts = smsManager.divideMessage(message)
            smsManager.sendMultipartTextMessage(phone, null, parts, null, null)

            Result.success("SMS Sent Successfully")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}