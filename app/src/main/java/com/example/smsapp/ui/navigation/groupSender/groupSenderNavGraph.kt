package com.example.smsapp.ui.navigation.groupSender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.groupSenderNavGraph(
    navController: NavController,
    openDrawer: () -> Unit
) {
    groupSenderListGraph(navController, openDrawer)
    groupSenderThreadGraph(navController)
}