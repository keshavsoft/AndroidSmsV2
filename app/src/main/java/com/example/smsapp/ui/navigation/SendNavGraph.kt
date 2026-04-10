package com.example.smsapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smsapp.AppScreen
import com.example.smsapp.ui.sendsms.v1.SendSmsScreenV1
import com.example.smsapp.ui.sendsms.v2.SendSmsScreenV2
import com.example.smsapp.ui.sendsms.v3.SendSmsScreenV3

fun NavGraphBuilder.sendGraph(
    openDrawer: () -> Unit
) {

    composable(AppScreen.SendV1.route) {
        SendSmsScreenV1(openDrawer = openDrawer)
    }

    composable(AppScreen.SendV2.route) {
        SendSmsScreenV2(openDrawer = openDrawer)
    }

    composable(
        route = AppScreen.SendV3.route + "?phone={phone}&msg={msg}",
        arguments = listOf(
            navArgument("phone") { defaultValue = "" },
            navArgument("msg") { defaultValue = "" }
        )
    ) { backStack ->

        val phone = backStack.arguments?.getString("phone") ?: ""
        val msg = backStack.arguments?.getString("msg") ?: ""

        SendSmsScreenV3(
            openDrawer = openDrawer,
            prefillPhone = phone,
            prefillMessage = msg
        )
    }

    composable(
        route = AppScreen.SendV4.route + "?phone={phone}&msg={msg}",
        arguments = listOf(
            navArgument("phone") { defaultValue = "" },
            navArgument("msg") { defaultValue = "" }
        )
    ) { backStack ->

        val phone = backStack.arguments?.getString("phone") ?: ""
        val msg = backStack.arguments?.getString("msg") ?: ""

        SendSmsScreenV3(
            openDrawer = openDrawer,
            prefillPhone = phone,
            prefillMessage = msg
        )
    }

}