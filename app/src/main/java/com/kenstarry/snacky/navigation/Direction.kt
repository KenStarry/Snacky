package com.kenstarry.snacky.navigation

import androidx.navigation.NavHostController

class Direction(
    navHostController: NavHostController
) {

    //  navigate to route
    val navigateToRoute: (
        route: String,
        popRoute: String?
    ) -> Unit = { route, popRoute ->

        if (popRoute != null) {

            navHostController.navigate(route = route) {
                popUpTo(route = popRoute)
                launchSingleTop = true
            }

        } else {
            navHostController.navigate(route = route)
        }
    }

    //  navigate back
    val navigateUp: () -> Unit = {
        navHostController.navigateUp()
    }
}














