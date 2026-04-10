package com.example.smsapp.contacts.data

import android.content.Context
import android.provider.ContactsContract
import com.example.smsapp.contacts.common.normalizePhone

fun loadContacts(context: Context): Map<String, String> {
    val map = mutableMapOf<String, String>()

    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        arrayOf(
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        ),
        null,
        null,
        null
    )

    cursor?.use {
        val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        while (it.moveToNext()) {
            val number = it.getString(numberIndex)
            val name = it.getString(nameIndex)
            map[normalizePhone(number)] = name
        }
    }

    return map
}