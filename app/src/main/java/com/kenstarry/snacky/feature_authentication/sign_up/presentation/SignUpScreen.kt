package com.kenstarry.snacky.feature_authentication.sign_up.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.presentation.components.BackPressTopBar
import com.kenstarry.snacky.feature_authentication.sign_up.presentation.components.*
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    mainNavHostController: NavHostController
) {

    val direction = Direction(mainNavHostController)

    Scaffold(
        topBar = {
            BackPressTopBar(
                title = "Create Account",
                onBackPressed = { direction.navigateUp() }
            )
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
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
            ) {

                //  image picker
                ImagePicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onImageClicked = {
                        //  open image picker dialog
                    }
                )

                //  username
                UsernameSection()

                //  email address
                EmailSection()

                //  password
                PasswordSection()

                //  confirm password
                ConfirmPasswordSection()

                //  create account button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        //  login user
                    }) {
                        Text(
                            text = "create account",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }

            }

        }

    }

}