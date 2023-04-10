package com.kenstarry.snacky.navigation.screens

import com.kenstarry.snacky.navigation.NavConstants

sealed class Screen(val route: String) {

    object Splash : Screen(NavConstants.SPLASH_SCREEN_ROUTE)

    object Main : Screen(NavConstants.MAIN_SCREEN_ROUTE)

    object Details : Screen("${NavConstants.DETAILS_SCREEN_ROUTE}/{category}/{title}") {

        fun passSnackTitleAndCategory(
            category: String,
            title: String
        ) = "${NavConstants.DETAILS_SCREEN_ROUTE}/$category/$title"
    }

    object Category : Screen("${NavConstants.CATEGORY_SCREEN_ROUTE}/{category}") {
        fun passCategory(category: String) = "${NavConstants.CATEGORY_SCREEN_ROUTE}/$category"
    }

    object Login : Screen(NavConstants.LOGIN_SCREEN_ROUTE)

    object SignUp : Screen(NavConstants.SIGN_UP_SCREEN_ROUTE)
}
