package com.example.smsapp.data

data class SmsMessage(
    val address: String,
    val body: String,
    val date: String,
    val type: Int=0
)