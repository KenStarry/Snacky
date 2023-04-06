package com.kenstarry.snacky.feature_main.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.screens.bottom_nav_screens.BottomNavScreens
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun MainBottomBar(
    innerNavHostController: NavHostController
) {

    val direction = Direction(innerNavHostController)

    val screens = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Favourites,
        BottomNavScreens.Cart,
        BottomNavScreens.Settings,
    )

    //  get current destination
    val navBackStackEntry by innerNavHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    //  check if any of the screens has a route away from the bottom bar
    val containsBottomBarDestination = screens.any { screen ->
        screen.route == currentDestination?.route
    }

    var isBottomBarVisible by remember {
        mutableStateOf(true)
    }

    isBottomBarVisible = containsBottomBarDestination

    AnimatedVisibility(visible = isBottomBarVisible) {

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ) {

            screens.forEach { screen ->
                MainBottomBarItem(
                    currentDestination = currentDestination,
                    screen = screen,
                    onBottomBarItemClicked = {
                        direction.navigateToRoute(
                            screen.route,
                            BottomNavScreens.Home.route
                        )
                    }
                )
            }

        }
    }

}





































