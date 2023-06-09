package com.kenstarry.snacky.feature_favourites.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.components.CenterBackTopBar
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_favourites.presentation.components.FavouriteSnacks
import com.kenstarry.snacky.feature_favourites.presentation.components.SortTitleSection
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.NavConstants
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController,
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)
    val snackBarHostState = remember { SnackbarHostState() }

    val currentUser = coreVM.getCurrentUser()
    coreVM.onEvent(CoreEvents.GetUserDetails(
        email = currentUser?.email ?: "no email",
        onResponse = {}
    ))

    Scaffold(
        topBar = {
            CenterBackTopBar(
                title = "Favourites",
                titleIcon = Icons.Filled.Favorite,
                onActionIconPressed = {},
                onBackPressed = {
                    direction.navigateToRoute(
                        NavConstants.MAIN_ROUTE,
                        NavConstants.MAIN_ROUTE
                    )
                }
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
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            coreVM.userDetails.value?.let { user ->

                // main content
                AnimatedVisibility(visible = user.userSnackFavourites.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .padding(MaterialTheme.spacing.medium),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                    ) {

                        // sort title
                        SortTitleSection()

                        // favourite items
                        FavouriteSnacks(
                            snackBarHostState = snackBarHostState,
                            direction = direction,
                            email = user.userEmail,
                            favouriteSnacks = user.userSnackFavourites
                        )

                    }
                }

                // blank message
                AnimatedVisibility(visible = user.userSnackFavourites.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .padding(MaterialTheme.spacing.medium),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.undraw_favourite),
                            contentDescription = "favourite",
                            modifier = Modifier
                                .fillMaxSize(0.6f)
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                        Text(
                            text = "All your favourite snacks appear here!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                        )

                    }
                }

            }

        }
    }

}