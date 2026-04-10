package com.example.smsapp.ui.navigation.incoming

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.smsapp.AppScreen
import com.example.smsapp.ui.incoming.v8.IncomingScreenV8
import android.net.Uri
import com.example.smsapp.ui.incoming.v9.IncomingScreenV9

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.incomingListGraph(
    navController: NavController,
    openDrawer: () -> Unit
) {
       composable(AppScreen.InboxIncomingV8.route) {
        IncomingScreenV8(
            openDrawer = openDrawer,
            navigateToThread = { number, name ->
                val n = Uri.encode(number)
                val t = Uri.encode(name)
                navController.navigate("incoming_v8_thread?number=$n&name=$t")
            }
        )
    }

    composable(AppScreen.InboxIncomingV9.route) {
        IncomingScreenV9(
            openDrawer = openDrawer,
            navigateToThread = { number, name ->
                val n = Uri.encode(number)
                val t = Uri.encode(name)
                navController.navigate("incoming_v9_thread?number=$n&name=$t")
            }
        )
    }

}