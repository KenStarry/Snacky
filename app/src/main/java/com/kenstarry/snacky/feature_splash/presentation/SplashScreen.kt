package com.kenstarry.snacky.feature_splash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    mainNavHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)

    LaunchedEffect(key1 = true) {

        delay(2000)

        //  navigate to either main route or login route
        if (coreVM.isUserLoggedIn()) {
            direction.navigateToRoute(
                NavConstants.MAIN_ROUTE,
                NavConstants.SPLASH_ROUTE
            )
        } else {
            direction.navigateToRoute(
                NavConstants.AUTHENTICATION_ROUTE,
                NavConstants.SPLASH_ROUTE
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = buildAnnotatedString {

                append("Sna")

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) { append("cky") }
            },
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

    }

}





























