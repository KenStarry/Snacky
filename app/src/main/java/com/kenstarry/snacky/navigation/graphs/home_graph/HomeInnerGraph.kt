package com.kenstarry.snacky.navigation.graphs.home_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kenstarry.snacky.feature_cart.presentation.CartScreen
import com.kenstarry.snacky.feature_favourites.presentation.FavouritesScreen
import com.kenstarry.snacky.feature_home.presentation.HomeScreen
import com.kenstarry.snacky.feature_settings.presentation.SettingsScreen
import com.kenstarry.snacky.navigation.screens.bottom_nav_screens.BottomNavScreens

@Composable
fun HomeInnerGraph(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController
) {

    NavHost(
        navController = innerNavHostController,
        startDestination = BottomNavScreens.Home.route
    ) {

        //  Home screen
        composable(route = BottomNavScreens.Home.route) {
            HomeScreen(
                mainNavHostController = mainNavHostController,
                innerNavHostController = innerNavHostController
            )
        }

        //  Favourites screen
        composable(route = BottomNavScreens.Favourites.route) {
            FavouritesScreen(
                mainNavHostController = mainNavHostController,
                innerNavHostController = innerNavHostController
            )
        }

        //  Cart screen
        composable(route = BottomNavScreens.Cart.route) {
            CartScreen(
                mainNavHostController = mainNavHostController,
                innerNavHostController = innerNavHostController
            )
        }

        //  Settings screen
        composable(route = BottomNavScreens.Settings.route) {
            SettingsScreen(
                mainNavHostController = mainNavHostController,
                innerNavHostController = innerNavHostController
            )
        }
    }

}


























