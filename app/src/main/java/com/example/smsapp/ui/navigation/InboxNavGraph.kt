package com.example.smsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.smsapp.AppScreen
import com.example.smsapp.ui.inbox.v1.InboxScreenV1
import com.example.smsapp.ui.inbox.v2.InboxScreenV2

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.InboxGraph(
    openDrawer: () -> Unit
) {

    composable(AppScreen.InboxV1.route) {
        InboxScreenV1(openDrawer = openDrawer)
    }

    composable(AppScreen.InboxV2.route) {
        InboxScreenV2(openDrawer = openDrawer)
    }
}