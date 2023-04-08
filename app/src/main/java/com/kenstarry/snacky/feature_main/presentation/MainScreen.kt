package com.kenstarry.snacky.feature_main.presentation

import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainScreen(
    mainNavHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val navController = rememberNavController()
    val context = LocalContext.current
    val view = LocalView.current

    var isKeyboardVisible by remember {
        mutableStateOf(false)
    }

    //  hide bottom bar on Soft input
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            isKeyboardVisible  = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

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
                if (!isKeyboardVisible) {
                    MainBottomBar(
                        innerNavHostController = navController,
                        cartItemsCount = coreVM.userDetails.value?.userCartItems?.size ?: 0
                    )
                }
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