package com.example.smsapp.ui.navigation.incoming

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smsapp.ui.common.conversation.ConvThreadAllLeftRight
import com.example.smsapp.ui.common.conversation.ConversationThreadAll
import com.example.smsapp.ui.common.conversation.ConversationThreadIncoming

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.incomingThreadGraph(
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

    composable(
        route = "incoming_v9_thread?number={number}&name={name}",
        arguments = listOf(
            navArgument("number") { defaultValue = "" },
            navArgument("name") { defaultValue = "" }
        )
    ) { backStack ->
        val number = backStack.arguments?.getString("number") ?: ""
        val name = backStack.arguments?.getString("name") ?: ""
        ConversationThreadAll(number, name) { navController.popBackStack() }
    }

    composable(
        route = "incoming_v10_thread?number={number}&name={name}",
        arguments = listOf(
            navArgument("number") { defaultValue = "" },
            navArgument("name") { defaultValue = "" }
        )
    ) { backStack ->
        val number = backStack.arguments?.getString("number") ?: ""
        val name = backStack.arguments?.getString("name") ?: ""
        ConvThreadAllLeftRight(number, name) { navController.popBackStack() }
    }

}