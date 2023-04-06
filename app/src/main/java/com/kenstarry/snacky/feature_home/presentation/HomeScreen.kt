package com.kenstarry.snacky.feature_home.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_home.domain.model.HomeEvents
import com.kenstarry.snacky.feature_home.presentation.components.CategoriesSection
import com.kenstarry.snacky.feature_home.presentation.components.HomeTopBar
import com.kenstarry.snacky.feature_home.presentation.components.PopularSection
import com.kenstarry.snacky.feature_home.presentation.components.SearchSection
import com.kenstarry.snacky.feature_home.presentation.viewmodel.HomeViewModel
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController,
) {

    val context = LocalContext.current
    val coreVM: CoreViewModel = hiltViewModel()
    val homeVM: HomeViewModel = hiltViewModel()

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
                        imageUri = user.userImageUri
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
                            .padding(
                                horizontal = MaterialTheme.spacing.medium,
                                vertical = MaterialTheme.spacing.small
                            ),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                    ) {

                        //  hungry text
                        Text(
                            text = "hungry? Order Up!",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
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
                            popularSnacks = homeVM.snacks.value
                        )

                    }

                }

            }

        }
    }

}




















