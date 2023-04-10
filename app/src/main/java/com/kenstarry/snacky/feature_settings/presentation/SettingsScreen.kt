package com.kenstarry.snacky.feature_settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.kenstarry.snacky.feature_settings.presentation.components.logout_section.AccountSection
import com.kenstarry.snacky.feature_settings.presentation.components.themes_section.ThemesSection
import com.kenstarry.snacky.feature_settings.presentation.viewmodel.SettingsViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.NavConstants
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

        //  logout alert dialog


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
                    .verticalScroll(rememberScrollState())
                    .padding(MaterialTheme.spacing.small),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.Start
            ) {

                //  Themes section
                ThemesSection(
                    settingsVM = settingsVM,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(MaterialTheme.spacing.small)
                )

                //  account section
                AccountSection(
                    settingsVM = settingsVM,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(MaterialTheme.spacing.small),
                    onLogout = {
                        //   navigate to login screen
                        direction.navigateToRoute(
                            NavConstants.AUTHENTICATION_ROUTE,
                            NavConstants.MAIN_ROUTE
                        )
                    },
                    onDeleteAccount = {
                        //   navigate to login screen
                        direction.navigateToRoute(
                            NavConstants.AUTHENTICATION_ROUTE,
                            NavConstants.MAIN_ROUTE
                        )
                    }
                )

                SalutationSection()

            }

        }

    }

}