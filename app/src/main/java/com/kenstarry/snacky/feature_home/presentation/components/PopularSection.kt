package com.kenstarry.snacky.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.launch

@Composable
fun PopularSection(
    snackBarHostState: SnackbarHostState,
    userDetails: User,
    popularSnacks: List<Snack>,
    onSnackClicked: (snack: Snack) -> Unit
) {

    val listState = rememberLazyListState()
    val context = LocalContext.current
    val detailsVM: DetailsViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {

        //  title
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Popular",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            //  see all button
            TextButton(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {

                Text(
                    text = "See all",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "see more button"
                )
            }

        }

        //  popular items
        LazyRow(
            content = {
                items(popularSnacks) { snack ->

                    val isFavouriteSnack = userDetails.userSnackFavourites
                        .contains(snack)

                    SnackItem(
                        context = context,
                        snack = snack,
                        isFavourite = isFavouriteSnack,
                        onSnackClicked = {
                            onSnackClicked(snack)
                        },
                        onFavoriteClicked = {
                            //  if is favourite
                            if (isFavouriteSnack) {
                                detailsVM.onEvent(DetailsEvents.UpdateSnackFavorites(
                                    email = userDetails.userEmail,
                                    snack = snack,
                                    isAdd = false,
                                    response = { res ->
                                        when (res) {
                                            is Response.Success -> {
                                                scope.launch {
                                                    snackBarHostState.showSnackbar(
                                                        message = "Snack removed from favourites.",
                                                        withDismissAction = true
                                                    )
                                                }
                                            }
                                            is Response.Failure -> {
                                                scope.launch {
                                                    snackBarHostState.showSnackbar(
                                                        message = "Something went wrong...",
                                                        withDismissAction = true
                                                    )
                                                }
                                            }
                                        }
                                    }
                                ))
                            } else {
                                detailsVM.onEvent(DetailsEvents.UpdateSnackFavorites(
                                    email = userDetails.userEmail,
                                    snack = snack,
                                    isAdd = true,
                                    response = {res ->
                                        when (res) {
                                            is Response.Success -> {
                                                scope.launch {
                                                    snackBarHostState.showSnackbar(
                                                        message = "Snack added to favourites.",
                                                        withDismissAction = true
                                                    )
                                                }
                                            }
                                            is Response.Failure -> {
                                                scope.launch {
                                                    snackBarHostState.showSnackbar(
                                                        message = "Something went wrong...",
                                                        withDismissAction = true
                                                    )
                                                }
                                            }
                                        }
                                    }
                                ))
                            }
                        }
                    )
                }
            },
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        )

    }
}

























