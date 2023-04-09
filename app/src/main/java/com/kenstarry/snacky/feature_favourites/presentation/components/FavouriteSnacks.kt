package com.kenstarry.snacky.feature_favourites.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.navigation.screens.Screen
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun FavouriteSnacks(
    snackBarHostState: SnackbarHostState,
    direction: Direction,
    email: String,
    favouriteSnacks: List<Snack>
) {

    //  getting all categories
    val snackCategories = favouriteSnacks.map { it.snackCategory }.distinct()
    val listState = rememberLazyListState()

    LazyColumn(
        content = {
            items(snackCategories) { category ->

                FavouriteCategoriesItem(
                    snackBarHostState = snackBarHostState,
                    email = email,
                    category = category,
                    favouriteSnacksUnderCategory = favouriteSnacks.filter { it.snackCategory == category },
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
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
    )

}