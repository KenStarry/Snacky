package com.kenstarry.snacky.feature_main.presentation

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_main.presentation.components.MainBottomBar
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.graphs.home_graph.HomeInnerGraph
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainNavHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val navController = rememberNavController()
    val context = LocalContext.current

    //  get user details
    val currentUser = coreVM.getCurrentUser()
    coreVM.onEvent(
        CoreEvents.GetUserDetails(
            email = currentUser?.email ?: "no email",
            onResponse = {}
        ))

    IntroShowCaseScaffold(
        showIntroShowCase = false,
        onShowCaseCompleted = { /*TODO*/ }
    ) {

        Scaffold(
            bottomBar = {
                MainBottomBar(
                    innerNavHostController = navController,
                    cartItemsCount =
                    coreVM.userDetails.value?.userCartItems?.size ?: 0
                )
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