package com.kenstarry.snacky.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.graphs.authentication_graph.authNavGraph
import com.kenstarry.snacky.navigation.graphs.home_graph.homeMainNavgraph
import com.kenstarry.snacky.navigation.graphs.splash_screen_graph.splashScreenNavGraph

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = NavConstants.SPLASH_ROUTE
    ) {

        splashScreenNavGraph(navHostController)
        homeMainNavgraph(navHostController)
        authNavGraph(navHostController)
    }

}































