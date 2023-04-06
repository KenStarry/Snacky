package com.kenstarry.snacky.feature_main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.snacky.feature_main.presentation.components.MainBottomBar
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.graphs.home_graph.HomeInnerGraph
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainNavHostController: NavHostController
) {

    val navController = rememberNavController()

    IntroShowCaseScaffold(
        showIntroShowCase = false,
        onShowCaseCompleted = { /*TODO*/ }
    ) {

        Scaffold(
            bottomBar = {
                MainBottomBar(innerNavHostController = navController)
            }
        ) { contentPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(contentPadding)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .padding(MaterialTheme.spacing.medium)
                ) {

                    //  call the inner navgraph
                    HomeInnerGraph(
                        mainNavHostController = mainNavHostController,
                        innerNavHostController = navController
                    )

                }

            }

        }

    }

}