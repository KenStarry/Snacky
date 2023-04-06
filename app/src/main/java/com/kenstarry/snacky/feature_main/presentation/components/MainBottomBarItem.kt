package com.kenstarry.snacky.feature_main.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.kenstarry.snacky.navigation.screens.bottom_nav_screens.BottomNavScreens

@Composable
fun RowScope.MainBottomBarItem(
    currentDestination: NavDestination?,
    screen: BottomNavScreens,
    onBottomBarItemClicked: () -> Unit
) {

    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        onClick = onBottomBarItemClicked,

        alwaysShowLabel = true,

        label = {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.bodySmall
            )
        },

        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Bottom Nav Icon"
            )
        },

        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f),
            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            indicatorColor = MaterialTheme.colorScheme.tertiary
        ),
    )

}