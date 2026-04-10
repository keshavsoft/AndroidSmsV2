package com.example.smsapp.ui.navigation.incoming

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smsapp.data.SmsReaderRepository
import com.example.smsapp.ui.common.conversation.ConvScreen
import com.example.smsapp.ui.common.conversation.ConvThreadAllLeftRight
import com.example.smsapp.ui.common.conversation.ConversationThreadAll
import com.example.smsapp.ui.common.conversation.ConversationThreadIncoming
import com.example.smsapp.ui.common.conversation.conversationScreen.ConversationScreen
import com.example.smsapp.utils.normalizeAddress

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
        Log.d("NAVCHECK", "number = $number")
        val name = backStack.arguments?.getString("name") ?: ""

        val context = LocalContext.current

        val repo = SmsReaderRepository(context)

        val messagesList = repo.getAllMessages()
            .filter { normalizeAddress(it.address) == number }
            .sortedBy { it.dateLong }

        ConversationScreen(
            messages = messagesList,   // real list, NOT number
            title = name,
            onSendClick = { msg ->
                // call send logic here
            },
            openDrawer = { navController.popBackStack() }
        )
    }

}