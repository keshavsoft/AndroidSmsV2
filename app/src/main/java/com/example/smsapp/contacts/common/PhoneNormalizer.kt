package com.example.smsapp.contacts.common

fun normalizePhone(number: String): String {
    var n = number.replace(" ", "")
        .replace("-", "")
        .replace("(", "")
        .replace(")", "")

    if (n.startsWith("+91")) n = n.substring(3)
    if (n.startsWith("91") && n.length > 10) n = n.substring(2)

    return n.takeLast(10)
}