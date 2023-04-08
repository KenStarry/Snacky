package com.kenstarry.snacky.feature_main.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.kenstarry.snacky.feature_cart.presentation.viewmodel.CartViewModel
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.screens.bottom_nav_screens.BottomNavScreens
import org.checkerframework.common.subtyping.qual.Bottom

@Composable
fun RowScope.MainBottomBarItem(
    currentDestination: NavDestination?,
    screen: BottomNavScreens,
    cartItemsCount: Int,
    onBottomBarItemClicked: () -> Unit
) {

    val cartVM: CartViewModel = hiltViewModel()

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
            if (screen.route == NavConstants.CART_BOTTOM_NAV_ROUTE) {
                val myCart = BottomNavScreens.Cart(cartItemsCount)
                //  add a badge counter
                BadgedBox(badge = {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ) {
                        Text(
                            text = myCart.badgeCount.toString(),
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            fontWeight = MaterialTheme.typography.bodySmall.fontWeight
                        )
                    }
                }) {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "Bottom Nav Icon"
                    )
                }
            } else {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = "Bottom Nav Icon"
                )
            }
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