package com.kenstarry.snacky.navigation.screens.bottom_nav_screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.kenstarry.snacky.navigation.NavConstants

sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    //  home screen
    object Home : BottomNavScreens(
        route = NavConstants.HOME_BOTTOM_NAV_ROUTE,
        title = "Home",
        icon = Icons.Outlined.HomeWork
    )

    //  home screen
    object Favourites : BottomNavScreens(
        route = NavConstants.FAVOURITES_BOTTOM_NAV_ROUTE,
        title = "Favourites",
        icon = Icons.Outlined.FavoriteBorder
    )

    //  home screen
    object Cart : BottomNavScreens(
        route = NavConstants.CART_BOTTOM_NAV_ROUTE,
        title = "My Cart",
        icon = Icons.Outlined.ShoppingCart
    )

    //  home screen
    object Settings : BottomNavScreens(
        route = NavConstants.SETTINGS_BOTTOM_NAV_ROUTE,
        title = "Settings",
        icon = Icons.Outlined.Settings
    )
}
