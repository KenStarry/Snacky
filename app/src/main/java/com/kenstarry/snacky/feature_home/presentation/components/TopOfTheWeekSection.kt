package com.kenstarry.snacky.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun TopOfTheWeekSection(
    userDetails: User,
    topSnacks: List<Snack>,
    onSnackClicked: (snack: Snack) -> Unit
) {

    val listState = rememberLazyListState()
    val context = LocalContext.current
    val detailsVM: DetailsViewModel = hiltViewModel()

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
                text = "Top of the Week!",
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
        LazyColumn(
            content = {
                items(topSnacks) { snack ->

                    val isFavouriteSnack = userDetails.userSnackFavourites
                        .contains(snack.snackName.title)

                    SnackItemAlt(
                        context = context,
                        snack = snack,
                        isFavourite = isFavouriteSnack,
                        onFavoriteClicked = {
                            //  if is favourite
                            if (isFavouriteSnack) {
                                detailsVM.onEvent(
                                    DetailsEvents.UpdateSnackFavorites(
                                        email = userDetails.userEmail,
                                        snackTitle = snack.snackName.title,
                                        isAdd = false,
                                        response = {}
                                    ))
                            } else {
                                detailsVM.onEvent(
                                    DetailsEvents.UpdateSnackFavorites(
                                        email = userDetails.userEmail,
                                        snackTitle = snack.snackName.title,
                                        isAdd = true,
                                        response = {}
                                    ))
                            }
                        },
                        onSnackClicked = {
                            onSnackClicked(snack)
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
                .height(500.dp),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        )

    }
}





























