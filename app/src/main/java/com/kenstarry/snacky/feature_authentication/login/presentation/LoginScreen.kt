package com.kenstarry.snacky.feature_authentication.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun LoginScreen(
    mainNavHostController: NavHostController
) {

    val loginScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(loginScrollState)
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {



    }

}


















