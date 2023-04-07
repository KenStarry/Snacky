package com.kenstarry.snacky.navigation.screens

import com.kenstarry.snacky.navigation.NavConstants

sealed class Screen(val route: String) {

    object Main : Screen(NavConstants.MAIN_SCREEN_ROUTE)

    object Details : Screen("${NavConstants.DETAILS_SCREEN_ROUTE}/{category}/{title}") {
        fun passSnackTitleAndCategory(
            category: String,
            title: String
        ) =
            "${NavConstants.DETAILS_SCREEN_ROUTE}/$category/$title"
    }

    object Login : Screen(NavConstants.LOGIN_SCREEN_ROUTE)

    object SignUp : Screen(NavConstants.SIGN_UP_SCREEN_ROUTE)
}
