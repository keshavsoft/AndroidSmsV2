package com.example.smsapp.ui.navigation.groupSender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smsapp.ui.incoming.common.conversation.ConversationThreadIncoming

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.groupSenderThreadGraph(
    navController: NavController
) {

    composable(
        route = "incoming_v8_thread?number={number}&name={name}",
        arguments = listOf(
            navArgument("number") { defaultValue = "" },
            navArgument("name") { defaultValue = "" }
        )
    ) { backStack ->
        val number = backStack.arguments?.getString("number") ?: ""
        val name = backStack.arguments?.getString("name") ?: ""
        ConversationThreadIncoming(number, name) { navController.popBackStack() }
    }

}