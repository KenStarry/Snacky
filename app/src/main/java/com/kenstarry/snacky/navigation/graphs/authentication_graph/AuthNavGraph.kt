package com.kenstarry.snacky.navigation.graphs.authentication_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.snacky.feature_authentication.login.presentation.LoginScreen
import com.kenstarry.snacky.feature_authentication.sign_up.presentation.SignUpScreen
import com.kenstarry.snacky.feature_main.presentation.MainScreen
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.screens.Screen

fun NavGraphBuilder.authNavGraph(
    mainNavHostController: NavHostController
) {

    navigation(
        startDestination = Screen.Login.route,
        route = NavConstants.AUTHENTICATION_ROUTE
    ) {

        //  login screen
        composable(route = Screen.Login.route) {
            LoginScreen(mainNavHostController = mainNavHostController)
        }

        //  login screen
        composable(route = Screen.SignUp.route) {
            SignUpScreen(mainNavHostController = mainNavHostController)
        }
    }

}