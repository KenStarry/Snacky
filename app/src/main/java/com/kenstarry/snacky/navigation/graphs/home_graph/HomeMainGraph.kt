package com.kenstarry.snacky.navigation.graphs.home_graph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.kenstarry.snacky.feature_details.presentation.DetailsScreen
import com.kenstarry.snacky.feature_main.presentation.MainScreen
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.screens.Screen

fun NavGraphBuilder.homeMainNavgraph(
    mainNavHostController: NavHostController
) {

    navigation(
        startDestination = Screen.Main.route,
        route = NavConstants.MAIN_ROUTE
    ) {

        //  main screen
        composable(route = Screen.Main.route) {
            MainScreen(mainNavHostController = mainNavHostController)
        }

        //  details screen
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen(
                mainNavHostController = mainNavHostController,
                snackTitle = it.arguments?.getString("title") ?: "no title"
            )
        }
    }
}