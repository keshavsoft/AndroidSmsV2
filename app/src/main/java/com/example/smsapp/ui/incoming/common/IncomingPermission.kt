package com.example.smsapp.ui.incoming.common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat

@Composable
fun IncomingPermission(
    context: Context,
    onGranted: () -> Unit
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) onGranted()
        }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) onGranted()
        else launcher.launch(Manifest.permission.READ_SMS)
    }
}