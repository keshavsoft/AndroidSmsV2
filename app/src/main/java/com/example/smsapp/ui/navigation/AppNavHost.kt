package com.example.smsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.smsapp.AppScreen
import com.example.smsapp.ui.navigation.groupSender.groupSenderNavGraph
import com.example.smsapp.ui.navigation.incoming.incomingGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    openDrawer: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.SendV1.route
    ) {
        sendGraph(openDrawer = openDrawer)

        InboxGraph(openDrawer = openDrawer)

        incomingGraph(
            navController = navController,
            openDrawer = openDrawer
        )

        outgoingGraph(
            navController = navController,
            openDrawer = openDrawer
        )

        groupSenderNavGraph(
            navController = navController,
            openDrawer = openDrawer
        )
    }
}