package com.kenstarry.snacky.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
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
import com.kenstarry.snacky.feature_home.domain.model.HomeEvents
import com.kenstarry.snacky.feature_home.presentation.viewmodel.HomeViewModel
import com.kenstarry.snacky.navigation.screens.Screen
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    snackBarHostState: SnackbarHostState,
    userDetails: User,
    detailsVM: DetailsViewModel,
    direction: com.kenstarry.snacky.navigation.Direction,
    onCloseClicked: () -> Unit
) {

    val homeVM: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val filteredSnacks = remember {
        mutableStateOf<List<Snack>>(emptyList())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  search bar
            SearchTopBar(
                onQueryInput = {
                    //  perform search
                    homeVM.onEvent(HomeEvents.SearchForSnacks(
                        query = it,
                        filteredSnacks = { snacks ->
                            filteredSnacks.value = snacks
                        }
                    ))
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                onCloseClicked()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

        //  lazy column with filtered snacks
        LazyColumn(
            content = {
                items(filteredSnacks.value) {

                    val isFavouriteSnack = userDetails.userSnackFavourites
                        .contains(it)

                    SnackItemAlt(
                        context = context,
                        snack = it,
                        isFavourite = isFavouriteSnack,
                        onFavoriteClicked = {
                            //  if is favourite
                            if (isFavouriteSnack) {
                                detailsVM.onEvent(
                                    DetailsEvents.UpdateSnackFavorites(
                                        email = userDetails.userEmail,
                                        snack = it,
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
                                detailsVM.onEvent(
                                    DetailsEvents.UpdateSnackFavorites(
                                        email = userDetails.userEmail,
                                        snack = it,
                                        isAdd = true,
                                        response = { res ->
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
                                            },
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
            },
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp * filteredSnacks.value.size),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        )

    }
}