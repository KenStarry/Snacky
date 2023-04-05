package com.kenstarry.snacky.navigation.graphs.home_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
    }
}