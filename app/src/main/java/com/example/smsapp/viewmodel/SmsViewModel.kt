package com.example.smsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smsapp.data.SmsMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.smsapp.data.SmsSenderRepository

data class SmsUiState(
    val phone: String = "",
    val message: String = "",
    val status: String? = null,
    val isSending: Boolean = false
)

class SmsViewModel : ViewModel() {

    private val repository = SmsSenderRepository()

    private val _uiState = MutableStateFlow(SmsUiState())
    val uiState: StateFlow<SmsUiState> = _uiState

    fun updatePhone(value: String) {
        _uiState.value = _uiState.value.copy(phone = value)
    }

    fun updateMessage(value: String) {
        _uiState.value = _uiState.value.copy(message = value)
    }

    fun sendSms() {
        val currentState = _uiState.value

        if (currentState.phone.isBlank() || currentState.message.isBlank()) {
            _uiState.value = currentState.copy(status = "Phone or Message empty")
            return
        }

        _uiState.value = currentState.copy(isSending = true)

        val result = repository.sendSms(currentState.phone, currentState.message)

        _uiState.value = if (result.isSuccess) {
            currentState.copy(
                status = result.getOrNull(),
                isSending = false
            )
        } else {
            currentState.copy(
                status = result.exceptionOrNull()?.message,
                isSending = false
            )
        }
    }

    fun prepareResend(sms: SmsMessage) {
        updatePhone(sms.address)
        updateMessage(sms.body)
    }

}