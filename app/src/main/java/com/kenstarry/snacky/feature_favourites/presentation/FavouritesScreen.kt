package com.kenstarry.snacky.feature_favourites.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.kenstarry.snacky.R
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun FavouritesScreen(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
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