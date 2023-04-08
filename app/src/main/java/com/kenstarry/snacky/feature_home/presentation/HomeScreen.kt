package com.kenstarry.snacky.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_home.domain.model.HomeEvents
import com.kenstarry.snacky.feature_home.presentation.components.CategoriesSection
import com.kenstarry.snacky.feature_home.presentation.components.HomeTopBar
import com.kenstarry.snacky.feature_home.presentation.components.PopularSection
import com.kenstarry.snacky.feature_home.presentation.components.TopOfTheWeekSection
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
    val direction = Direction(mainNavHostController)
    val directionInner = Direction(innerNavHostController)
    val systemController = rememberSystemUiController()

    systemController.setStatusBarColor(
        color = MaterialTheme.colorScheme.onPrimary
    )

    val currentUser = coreVM.getCurrentUser()

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
                        onSearchPressed = {},
                        onImageClicked = {}
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
                            .padding(
                                horizontal = MaterialTheme.spacing.medium,
                                vertical = MaterialTheme.spacing.small
                            ),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                    ) {

                        //  hungry text
                        Text(
                            text = "hungry? Order Up!",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )

                        //  search bar
//                        SearchSection()

                        //  snack categories
                        CategoriesSection(
                            context = context,
                            categories = homeVM.categories.value
                        )

                        //  popular section
                        PopularSection(
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




















