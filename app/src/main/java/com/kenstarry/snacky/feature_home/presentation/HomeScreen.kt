package com.kenstarry.snacky.feature_home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.feature_home.domain.model.HomeEvents
import com.kenstarry.snacky.feature_home.presentation.components.*
import com.kenstarry.snacky.feature_home.presentation.viewmodel.HomeViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.screens.Screen
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController,
) {

    val context = LocalContext.current
    val coreVM: CoreViewModel = hiltViewModel()
    val homeVM: HomeViewModel = hiltViewModel()
    val detailsVM: DetailsViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)
    val directionInner = Direction(innerNavHostController)
    val systemController = rememberSystemUiController()
    val snackBarHostState = remember { SnackbarHostState() }

    systemController.setStatusBarColor(
        color = MaterialTheme.colorScheme.onPrimary
    )

    val currentUser = coreVM.getCurrentUser()

    var isSearching by remember {
        mutableStateOf(false)
    }

    //  get current user details
    coreVM.onEvent(CoreEvents.GetUserDetails(
        email = currentUser?.email ?: "no email",
        onResponse = {}
    ))

    //  get all item categories
    homeVM.onEvent(HomeEvents.GetCategories(
        response = {}
    ))

    //  get all snacks categories
    homeVM.onEvent(HomeEvents.GetSnacks(
        response = {}
    ))

    coreVM.userDetails.value?.let { user ->
        IntroShowCaseScaffold(
            showIntroShowCase = false,
            onShowCaseCompleted = { /*TODO*/ }
        ) {

            Scaffold(
                topBar = {
                    HomeTopBar(
                        context = context,
                        userName = user.userName,
                        imageUri = user.userImageUri,
                        onSearchPressed = {
                            isSearching = true
                        },
                        onImageClicked = {}
                    )
                },
                snackbarHost = {
                    SnackbarHost(hostState = snackBarHostState) {

                        Snackbar(
                            snackbarData = it,
                            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                            actionColor = MaterialTheme.colorScheme.primary,
                            dismissActionContentColor = MaterialTheme.colorScheme.primary
                        )
                    }
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
                            .padding(
                                horizontal = MaterialTheme.spacing.medium,
                                vertical = MaterialTheme.spacing.small
                            ),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                    ) {

                        if (isSearching) {
                            SearchScreen(
                                snackBarHostState = snackBarHostState,
                                userDetails = user,
                                detailsVM = detailsVM,
                                direction = direction,
                                onCloseClicked = {
                                    isSearching = false
                                }
                            )
                        } else {

                            //  hungry text
                            Text(
                                text = "hungry? Order Up!",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                            )

                            //  snack categories
                            CategoriesSection(
                                context = context,
                                categories = homeVM.categories.value,
                                onCategoryClicked = {
                                    direction.navigateToRoute(
                                        Screen.Category.passCategory(it),
                                        null
                                    )
                                }
                            )

                            //  popular section
                            PopularSection(
                                snackBarHostState = snackBarHostState,
                                userDetails = user,
                                popularSnacks = homeVM.snacks.value,
                                onSnackClicked = {
                                    //  open details for that snack
                                    direction.navigateToRoute(
                                        Screen.Details.passSnackTitleAndCategory(
                                            it.snackCategory,
                                            it.snackName.title
                                        ),
                                        null
                                    )
                                }
                            )

                            //  top of the week
                            TopOfTheWeekSection(
                                snackBarHostState = snackBarHostState,
                                userDetails = user,
                                topSnacks = homeVM.snacks.value,
                                onSnackClicked = {
                                    //  open details for that snack
                                    direction.navigateToRoute(
                                        Screen.Details.passSnackTitleAndCategory(
                                            it.snackCategory,
                                            it.snackName.title
                                        ),
                                        null
                                    )
                                }
                            )
                        }

                    }

                }

            }

        }
    }

}




















