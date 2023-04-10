package com.kenstarry.snacky.feature_settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.presentation.components.CenterBackTopBar
import com.kenstarry.snacky.feature_settings.presentation.components.SalutationSection
import com.kenstarry.snacky.feature_settings.presentation.components.themes_section.ThemesSection
import com.kenstarry.snacky.feature_settings.presentation.viewmodel.SettingsViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController,
) {

    val settingsVM: SettingsViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)

    Scaffold(
        topBar = {
            CenterBackTopBar(
                title = "Settings",
                titleIcon = Icons.Outlined.Settings,
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.Start
            ) {

                ThemesSection(
                    settingsVM = settingsVM,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp)
                )

                SalutationSection()

            }

        }

    }

}