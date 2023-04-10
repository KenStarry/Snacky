package com.kenstarry.snacky.navigation.graphs.splash_screen_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.snacky.feature_splash.presentation.SplashScreen
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.screens.Screen

fun NavGraphBuilder.splashScreenNavGraph(
    mainNavHostController: NavHostController
) {

    navigation(
        startDestination = Screen.Splash.route,
        route = NavConstants.SPLASH_ROUTE
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(mainNavHostController = mainNavHostController)
        }
    }
}