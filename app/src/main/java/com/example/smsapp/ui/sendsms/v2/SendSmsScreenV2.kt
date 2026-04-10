package com.example.smsapp.ui.sendsms.v2

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smsapp.ui.components.AppTopBar
import com.example.smsapp.viewmodel.SmsViewModel
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.draw.scale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendSmsScreenV2(
    viewModel: SmsViewModel = viewModel(),
    openDrawer: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val commonHeadLabel="Send SMS V2"

    Scaffold(
        topBar = {
            AppTopBar(
                title = commonHeadLabel,
                showBack = false,
                onMenuClick = openDrawer
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            TextField(
                value = state.phone,
                onValueChange = viewModel::updatePhone,
                label = { Text("Phone Number") },
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.message,
                onValueChange = viewModel::updateMessage,
                label = { Text("Message") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(20.dp),
                maxLines = 5,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            val sending by remember { derivedStateOf { state.isSending } }

            FilledTonalButton(
                onClick = viewModel::sendSms,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .scale(
                        animateFloatAsState(
                            targetValue = if (sending) 0.95f else 1f,
                            label = ""
                        ).value
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                AnimatedContent(targetState = sending, label = "") { loading ->
                    if (loading) {
                        CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                    } else {
                        Text("Send Message")
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            state.status?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}