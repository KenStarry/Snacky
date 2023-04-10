package com.kenstarry.snacky.feature_authentication.sign_up.presentation

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.core.presentation.components.BackPressTopBar
import com.kenstarry.snacky.core.presentation.components.LoadingCircle
import com.kenstarry.snacky.feature_authentication.AuthConstants
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.RegistrationFormEvents
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.SignUpEvents
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation.ValidationEvent
import com.kenstarry.snacky.feature_authentication.sign_up.presentation.components.*
import com.kenstarry.snacky.feature_authentication.sign_up.presentation.viewmodel.SignUpViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.flow.collect
import kotlin.math.sign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    mainNavHostController: NavHostController
) {

    val direction = Direction(mainNavHostController)
    val context = LocalContext.current
    val signUpVM: SignUpViewModel = hiltViewModel()

    var userImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { imageUri ->
            userImageUri = imageUri
        }
    )

    //  get the validation result
    LaunchedEffect(Unit) {

        signUpVM.validationEvents.collect { event ->

            when (event) {
                is ValidationEvent.Success -> {

                    signUpVM.onEvent(
                        SignUpEvents.CreateAccount(
                            user = User(
                                userImageUri = "",
                                userName = signUpVM.formState.username,
                                userEmail = signUpVM.formState.email,
                                userPassword = signUpVM.formState.password,
                                userSnackFavourites = emptyList(),
                                userSnackOrders = emptyList(),
                                userCartItems = emptyList(),
                            ),
                            response = { res ->
                                when (res) {

                                    is Response.Success -> {
                                        //  upload image to firestore
                                        signUpVM.onEvent(SignUpEvents.UploadImage(
                                            uri = userImageUri.toString(),
                                            context = context,
                                            storageRef = "users/${signUpVM.formState.email}",
                                            collectionName = AuthConstants.USERS_COLLECTION,
                                            documentName = signUpVM.formState.email,
                                            subCollectionName = null,
                                            subCollectionDocument = null,
                                            fieldToUpdate = "userImageUri",
                                            onResponse = {}
                                        ))

                                        Toast.makeText(
                                            context,
                                            "account created successfully!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        isLoading = false

                                        //  navigate to main route
                                        direction.navigateToRoute(
                                            NavConstants.MAIN_ROUTE,
                                            NavConstants.AUTHENTICATION_ROUTE
                                        )
                                    }
                                    is Response.Failure -> {

                                        isLoading = false

                                        Toast.makeText(
                                            context,
                                            "${res.error}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        )
                    )
                }
                is ValidationEvent.Failure -> {
                    isLoading = false
                }

            }
        }
    }

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
                    .verticalScroll(rememberScrollState())
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
            ) {

                //  image picker
                ImagePicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    imageUri = userImageUri,
                    onImageClicked = {
                        //  open image picker dialog
                        launcher.launch("image/*")
                    }
                )

                //  username
                UsernameSection(
                    signUpVM = signUpVM
                )

                //  email address
                EmailSection(
                    signUpVM = signUpVM
                )

                //  password
                PasswordSection(
                    signUpVM = signUpVM
                )

                //  confirm password
                ConfirmPasswordSection(
                    signUpVM = signUpVM
                )

                //  create account button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {

                    if (isLoading) {
                        LoadingCircle(
                            primaryColor = MaterialTheme.colorScheme.primary,
                            tertiaryColor = MaterialTheme.colorScheme.tertiary
                        )
                    } else {
                        Button(onClick = {

                            isLoading = true

                            //  create user account
                            signUpVM.onFormEvent(RegistrationFormEvents.Submit)

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

}