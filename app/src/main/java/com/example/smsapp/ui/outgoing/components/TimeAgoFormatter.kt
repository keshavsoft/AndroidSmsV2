package com.example.smsapp.ui.outgoing.components

import java.text.SimpleDateFormat
import java.util.*

fun formatTimeAgo(dateString: String): String {

    val formats = listOf(
        "dd/MM/yyyy HH:mm:ss",
        "dd MMM yyyy HH:mm:ss",
        "dd MMM yyyy, hh:mm a",
        "yyyy-MM-dd HH:mm:ss"
    )

    var time: Long? = null

    for (f in formats) {
        try {
            time = SimpleDateFormat(f, Locale.getDefault()).parse(dateString)?.time
            if (time != null) break
        } catch (_: Exception) {}
    }

    if (time == null) return dateString

    val diff = System.currentTimeMillis() - time
    val min = diff / 60000
    val hr = diff / 3600000
    val day = diff / 86400000

    return when {
        min < 1 -> "just now"
        min < 60 -> "$min min ago"
        hr < 24 -> "$hr hr ago"
        day == 1L -> "yesterday"
        day < 7 -> "$day days ago"
        else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(time))
    }
}