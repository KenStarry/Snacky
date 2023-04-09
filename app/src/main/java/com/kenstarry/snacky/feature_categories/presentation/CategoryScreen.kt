package com.kenstarry.snacky.feature_categories.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.presentation.components.CenterBackTopBar
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    mainNavHostController: NavHostController,
    category: String
) {

    Scaffold(
        topBar = {
            CenterBackTopBar(
                title = "Category",
                titleIcon = Icons.Outlined.Fastfood,
                actions = emptyArray(),
                onBackPressed = { /*TODO*/ },
                onActionIconPressed = {}
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
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
            ) {



            }

        }

    }
    
}