package com.kenstarry.snacky.feature_favourites.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.presentation.components.CenterBackTopBar
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController,
) {

    Scaffold(
        topBar = {
            CenterBackTopBar(
                title = "Favourites",
                titleIcon = Icons.Filled.Favorite,
                onActionIconPressed = {},
                onBackPressed = {}
            )
        }
    ) { contentPadding ->

     Box(
         modifier = Modifier
             .fillMaxSize()
             .background(MaterialTheme.colorScheme.onPrimary)
             .padding(contentPadding)
     ) {

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