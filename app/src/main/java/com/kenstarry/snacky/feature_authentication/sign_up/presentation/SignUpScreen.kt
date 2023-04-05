package com.kenstarry.snacky.feature_authentication.sign_up.presentation

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
import com.kenstarry.snacky.core.presentation.components.BackPressTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    mainNavHostController: NavHostController
) {

    Scaffold(
        topBar = {
            BackPressTopBar(title = "Create Account")
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
            ) {

            }

        }

    }

}