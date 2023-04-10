package com.kenstarry.snacky.feature_settings.presentation.components.logout_section

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_settings.domain.model.SettingsEvents
import com.kenstarry.snacky.feature_settings.presentation.components.SectionTitle
import com.kenstarry.snacky.feature_settings.presentation.util.SettingsConstants
import com.kenstarry.snacky.feature_settings.presentation.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSection(
    modifier: Modifier = Modifier,
    settingsVM: SettingsViewModel,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
) {

    val context = LocalContext.current
    val coreVM: CoreViewModel = hiltViewModel()
    val currentUser = coreVM.getCurrentUser()
    coreVM.onEvent(CoreEvents.GetUserDetails(
        email = currentUser?.email ?: "no email",
        onResponse = {}
    ))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SectionTitle(
                title = "Account",
                icon = Icons.Outlined.DarkMode,
                iconColor = MaterialTheme.colorScheme.primary,
                iconBackground = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            //  content
            LazyColumn(
                content = {
                    items(
                        SettingsConstants.accountOptions
                    ) {

                        AccountItem(
                            title = it.title,
                            icon = it.icon,
                            onAccountItemClicked = {

                                when (it) {
                                    SettingsConstants.accountOptions[0] -> {
                                        //  logout of account
                                        settingsVM.onEvent(SettingsEvents.Logout(
                                            onResponse = { res ->
                                                when (res) {
                                                    is Response.Success -> {
                                                        onLogout()
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

                                    SettingsConstants.accountOptions[1] -> {

                                        coreVM.userDetails.value?.let { user ->
                                            //  delete user account
                                            settingsVM.onEvent(SettingsEvents.DeleteAccount(
                                                email = user.userEmail,
                                                onResponse = { res ->
                                                    when (res) {
                                                        is Response.Success -> {
                                                            onDeleteAccount()
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
                                    }
                                }
                            }
                        )
                    }
                },
                state = rememberLazyListState(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .height((50.dp + 16.dp) * SettingsConstants.accountOptions.size)
            )

        }

    }

}

























