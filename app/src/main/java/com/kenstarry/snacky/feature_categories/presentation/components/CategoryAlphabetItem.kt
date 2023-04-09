package com.kenstarry.snacky.feature_categories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.feature_home.presentation.components.SnackItemAlt
import com.kenstarry.snacky.navigation.screens.Screen
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.launch

@Composable
fun CategoryAlphabetItem(
    snackBarHostState: SnackbarHostState,
    userDetails: User,
    detailsVM: DetailsViewModel,
    direction: com.kenstarry.snacky.navigation.Direction,
    alphabet: String,
    snacksInAlphabet: List<Snack>
) {

    val listState = rememberLazyListState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
    ) {

        Text(
            text = alphabet,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )

        //  snack items
        LazyColumn(
            content = {
                items(snacksInAlphabet.sortedBy { it.snackName.title }) {

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
                                        response = {}
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
                                                            message = "Added to favourites!",
                                                            withDismissAction = true
                                                        )
                                                    }
                                                }
                                                is Response.Failure -> {}
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
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(MaterialTheme.colorScheme.onSecondary)
                    )
                }
            },
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp * snacksInAlphabet.size),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        )

    }

}






























