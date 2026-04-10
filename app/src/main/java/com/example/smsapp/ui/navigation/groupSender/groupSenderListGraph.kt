package com.example.smsapp.ui.navigation.groupSender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.smsapp.AppScreen
import com.example.smsapp.ui.groupSender.v1.GroupSenderScreenV1
import com.example.smsapp.ui.groupSender.v2.GroupSenderScreenV2
import com.example.smsapp.ui.groupSender.v3.GroupSenderScreenV3
import com.example.smsapp.ui.groupSender.v4.GroupSenderScreenV4

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.groupSenderListGraph(
    navController: NavController,
    openDrawer: () -> Unit
) {
    composable(AppScreen.GroupSenderV1.route) {
        GroupSenderScreenV1()
    }

    composable(AppScreen.GroupSenderV2.route) {
        GroupSenderScreenV2(openDrawer = openDrawer)
    }

    composable(AppScreen.GroupSenderV3.route) {
        GroupSenderScreenV3(openDrawer = openDrawer)
    }

    composable(AppScreen.GroupSenderV4.route) {
        GroupSenderScreenV4(openDrawer = openDrawer)
    }
}