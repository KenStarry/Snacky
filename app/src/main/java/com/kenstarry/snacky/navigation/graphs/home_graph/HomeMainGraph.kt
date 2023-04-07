package com.kenstarry.snacky.navigation.graphs.home_graph

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_details.presentation.DetailsScreen
import com.kenstarry.snacky.feature_main.presentation.MainScreen
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.screens.Screen
import kotlinx.serialization.json.Json

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
                navArgument("category") {
                    type = NavType.StringType
                },
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen(
                mainNavHostController = mainNavHostController,
                snackCategory = it.arguments?.getString("category") ?: "no title",
                snackTitle = it.arguments?.getString("title") ?: "no title"
            )
        }
    }
}