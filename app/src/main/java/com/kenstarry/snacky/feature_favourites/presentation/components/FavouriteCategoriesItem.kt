package com.kenstarry.snacky.feature_favourites.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.feature_home.presentation.components.SnackItem
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.launch

@Composable
fun FavouriteCategoriesItem(
    snackBarHostState: SnackbarHostState,
    email: String,
    category: String,
    favouriteSnacksUnderCategory: List<Snack>,
    onSnackClicked: (snack: Snack) -> Unit
) {

    val lazyListState = rememberLazyListState()
    val context = LocalContext.current
    val detailsVM: DetailsViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {

        Text(
            text = category,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  favourites snacks list
        LazyRow(
            content = {
                items(favouriteSnacksUnderCategory) {
                    SnackItem(
                        context = context,
                        snack = it,
                        isFavourite = true,
                        onSnackClicked = {
                            onSnackClicked(it)
                        },
                        onFavoriteClicked = {
                            detailsVM.onEvent(
                                DetailsEvents.UpdateSnackFavorites(
                                email = email,
                                snack = it,
                                isAdd = false,
                                response = {res ->
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
                                                    message = "Something went wrong",
                                                    withDismissAction = true
                                                )
                                            }
                                        }
                                    }
                                }
                            ))
                        }
                    )
                }
            },
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        )

    }

}

































