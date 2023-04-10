package com.kenstarry.snacky.feature_settings.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.components.CenterBackTopBar
import com.kenstarry.snacky.core.presentation.components.CustomAlertDialog
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_settings.domain.model.SettingsEvents
import com.kenstarry.snacky.feature_settings.presentation.components.SalutationSection
import com.kenstarry.snacky.feature_settings.presentation.components.logout_section.AccountSection
import com.kenstarry.snacky.feature_settings.presentation.components.themes_section.ThemesSection
import com.kenstarry.snacky.feature_settings.presentation.model.AlertDialogStatus
import com.kenstarry.snacky.feature_settings.presentation.util.SettingsConstants
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
    val context = LocalContext.current
    val coreVM: CoreViewModel = hiltViewModel()
    val currentUser = coreVM.getCurrentUser()
    coreVM.onEvent(
        CoreEvents.GetUserDetails(
            email = currentUser?.email ?: "no email",
            onResponse = {}
        ))

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
        AnimatedVisibility(
            visible = settingsVM.alertDialogStatus.value.isDialogVisible &&
                    settingsVM.alertDialogStatus.value.dialogTitle == SettingsConstants.LOGOUT_DIALOG
        ) {

            CustomAlertDialog(
                icon = Icons.Outlined.Logout,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                title = "Logout",
                content = {
                    Text(
                        text = "Are you sure you want to logout of your account?",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )
                },
                onConfirm = {
                    //  logout of account
                    settingsVM.onEvent(
                        SettingsEvents.Logout(
                            onResponse = { res ->
                                when (res) {
                                    is Response.Success -> {

                                        //  close alert dialog
                                        settingsVM.onEvent(SettingsEvents.ToggleAlertDialog(
                                            alertDialogStatus = AlertDialogStatus(
                                                dialogTitle = SettingsConstants.LOGOUT_DIALOG,
                                                isDialogVisible = false
                                            )
                                        ))

                                        Toast.makeText(
                                            context,
                                            "Logged out successfully.",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        //   navigate to login screen
                                        direction.navigateToRoute(
                                            NavConstants.AUTHENTICATION_ROUTE,
                                            NavConstants.MAIN_ROUTE
                                        )
                                    }
                                    is Response.Failure -> {
                                        Toast.makeText(
                                            context,
                                            res.error.toString(),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        ))
                },
                onDismiss = {
                    //  close alert dialog
                    settingsVM.onEvent(SettingsEvents.ToggleAlertDialog(
                        alertDialogStatus = AlertDialogStatus(
                            dialogTitle = SettingsConstants.LOGOUT_DIALOG,
                            isDialogVisible = false
                        )
                    ))
                }
            )

        }

        //  delete account alert dialog
        AnimatedVisibility(
            visible = settingsVM.alertDialogStatus.value.isDialogVisible &&
                    settingsVM.alertDialogStatus.value.dialogTitle == SettingsConstants.DELETE_ACCOUNT_DIALOG
        ) {

            CustomAlertDialog(
                icon = Icons.Outlined.DeleteForever,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                title = "Delete Account",
                content = {
                    Text(
                        text = "Deleting your account is irreversible! Are you sure you want to proceed?",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )
                },
                onConfirm = {
                    //  delete account
                    coreVM.userDetails.value?.let { user ->
                        //  delete user account
                        settingsVM.onEvent(SettingsEvents.DeleteAccount(
                            email = user.userEmail,
                            onResponse = { res ->
                                when (res) {
                                    is Response.Success -> {

                                        //  close alert dialog
                                        settingsVM.onEvent(SettingsEvents.ToggleAlertDialog(
                                            alertDialogStatus = AlertDialogStatus(
                                                dialogTitle = SettingsConstants.DELETE_ACCOUNT_DIALOG,
                                                isDialogVisible = false
                                            )
                                        ))

                                        Toast.makeText(
                                            context,
                                            "Account deleted successfully.",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        //   navigate to login screen
                                        direction.navigateToRoute(
                                            NavConstants.AUTHENTICATION_ROUTE,
                                            NavConstants.MAIN_ROUTE
                                        )
                                    }
                                    is Response.Failure -> {
                                        Toast.makeText(
                                            context,
                                            res.error.toString(),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        ))
                    }
                },
                onDismiss = {
                    //  close alert dialog
                    settingsVM.onEvent(SettingsEvents.ToggleAlertDialog(
                        alertDialogStatus = AlertDialogStatus(
                            dialogTitle = SettingsConstants.DELETE_ACCOUNT_DIALOG,
                            isDialogVisible = false
                        )
                    ))
                }
            )

        }

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
                        //  open alert dialog
                        settingsVM.onEvent(SettingsEvents.ToggleAlertDialog(
                            alertDialogStatus = AlertDialogStatus(
                                dialogTitle = SettingsConstants.LOGOUT_DIALOG,
                                isDialogVisible = true
                            )
                        ))
                    },
                    onDeleteAccount = {

                        //  open alert dialog
                        settingsVM.onEvent(SettingsEvents.ToggleAlertDialog(
                            alertDialogStatus = AlertDialogStatus(
                                dialogTitle = SettingsConstants.DELETE_ACCOUNT_DIALOG,
                                isDialogVisible = true
                            )
                        ))
                    }
                )

                SalutationSection()

            }

        }

    }

}