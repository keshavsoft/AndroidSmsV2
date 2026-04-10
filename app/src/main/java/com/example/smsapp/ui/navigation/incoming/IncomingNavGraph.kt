package com.example.smsapp.ui.navigation.incoming

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.incomingGraph(
    navController: NavController,
    openDrawer: () -> Unit
) {
    incomingListGraph(navController, openDrawer)
    incomingThreadGraph(navController)
}