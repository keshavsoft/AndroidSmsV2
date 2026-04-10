package com.example.smsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.smsapp.AppScreen
import com.example.smsapp.ui.outgoing.v1.OutgoingScreenV1
import com.example.smsapp.ui.outgoing.v2.OutgoingScreenV2
import com.example.smsapp.ui.outgoing.v3.OutgoingScreenV3
import com.example.smsapp.ui.outgoing.v4.OutgoingScreenV4
import com.example.smsapp.ui.outgoing.v5.OutgoingScreenV5
import com.example.smsapp.ui.outgoing.v6.OutgoingScreenV6
import com.example.smsapp.ui.outgoing.v7.OutgoingScreenV7

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.outgoingGraph(
    navController: NavController,
    openDrawer: () -> Unit
) {

    composable(AppScreen.OutgoingV1.route) {
        OutgoingScreenV1(openDrawer = openDrawer)
    }

    composable(AppScreen.OutgoingV2.route) {
        OutgoingScreenV2(openDrawer = openDrawer)
    }

    composable(AppScreen.OutgoingV3.route) {

        OutgoingScreenV3(
            openDrawer = openDrawer,
            navigateToSend = { phone, msg ->
                navController.navigate(
                    AppScreen.SendV3.route + "?phone=$phone&msg=$msg"
                )
            }
        )
    }

    composable(AppScreen.OutgoingV4.route) {
        OutgoingScreenV4(
            openDrawer = openDrawer,
            navigateToSend = { phone, msg ->
                navController.navigate(
                    AppScreen.SendV3.route + "?phone=$phone&msg=$msg"
                )
            }
        )
    }

    composable(AppScreen.OutgoingV5.route) {
        OutgoingScreenV5(
            openDrawer = openDrawer,
            navigateToSend = { phone, msg ->
                navController.navigate(
                    AppScreen.SendV3.route + "?phone=$phone&msg=$msg"
                )
            }
        )
    }

    composable(AppScreen.OutgoingV6.route) {
        OutgoingScreenV6(
            openDrawer = openDrawer,
            navigateToSend = { phone, msg ->
                navController.navigate(
                    AppScreen.SendV3.route + "?phone=$phone&msg=$msg"
                )
            }
        )
    }

    composable(AppScreen.OutgoingV7.route) {
        OutgoingScreenV7(
            openDrawer = openDrawer,
            navigateToSend = { phone, msg ->
                navController.navigate(
                    AppScreen.SendV3.route + "?phone=$phone&msg=$msg"
                )
            }
        )
    }
}