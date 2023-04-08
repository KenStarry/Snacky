package com.kenstarry.snacky.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.graphs.authentication_graph.authNavGraph
import com.kenstarry.snacky.navigation.graphs.home_graph.homeMainNavgraph

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    isLoggedIn: Boolean
) {

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn)
            NavConstants.MAIN_ROUTE
        else
            NavConstants.AUTHENTICATION_ROUTE
    ) {

        homeMainNavgraph(navHostController)
        authNavGraph(navHostController)
    }

}































