package com.kenstarry.snacky.feature_authentication.login.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.presentation.components.AnnotatedClickableString
import com.kenstarry.snacky.core.presentation.components.CustomTextField
import com.kenstarry.snacky.feature_authentication.AuthConstants
import com.kenstarry.snacky.feature_authentication.login.domain.model.LoginEvents
import com.kenstarry.snacky.feature_authentication.login.domain.model.form.LoginFormEvents
import com.kenstarry.snacky.feature_authentication.login.presentation.viewmodel.LoginViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.navigation.screens.Screen
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun LoginScreen(
    mainNavHostController: NavHostController
) {

    val loginScrollState = rememberScrollState()
    val direction = Direction(mainNavHostController)
    val loginVM: LoginViewModel = hiltViewModel()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(loginScrollState)
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
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

        //  svg image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.undraw_ice_cream_s_2_rh),
                contentDescription = "Snacky svg image",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(230.dp)
            )
        }

        //  login text
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  email text field
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
        ) {

            //  Email Address
            CustomTextField(
                textFieldValue = loginVM.formState.email,
                startIcon = Icons.Outlined.AlternateEmail,
                endIcon = null,
                placeholder = "Email Address",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                onInput = {
                    loginVM.onFormEvent(LoginFormEvents.OnLoginEmailChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            //  Password
            CustomTextField(
                textFieldValue = loginVM.formState.password,
                startIcon = Icons.Outlined.Key,
                endIcon = null,
                placeholder = "Password",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                isPassword = true,
                onInput = {
                    loginVM.onFormEvent(LoginFormEvents.OnLoginPasswordChanged(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            //  login button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    //  login user
                    loginVM.onEvent(LoginEvents.LoginUser(
                        email = loginVM.formState.email,
                        password = loginVM.formState.password,
                        onResponse = { res ->
                            when (res) {

                                is Response.Success -> {
                                    direction.navigateToRoute(
                                        NavConstants.MAIN_ROUTE,
                                        NavConstants.AUTHENTICATION_ROUTE
                                    )

                                    Toast.makeText(
                                        context,
                                        "Welcome back!",
                                        Toast.LENGTH_SHORT
                                    ).show()
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
                }) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            //  create account button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                AnnotatedClickableString(
                    normalText = "New to Snacky? ",
                    clickableText = "create account",
                    primaryColor = MaterialTheme.colorScheme.primary
                ) {
                    //  open sign up screen
                    direction.navigateToRoute(
                        Screen.SignUp.route,
                        null
                    )
                }
            }
        }
    }

}


















