package com.kenstarry.snacky.feature_details.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.presentation.components.CoilImage
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsImage(
    context: Context,
    detailsViewModel: DetailsViewModel,
    snackImage: String,
    primaryColor: Color,
    onFavoriteClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            CoilImage(
                context = context,
                imageUri = snackImage.toUri(),
                placeholder = R.drawable.undraw_ice_cream_s_2_rh,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                contentScale = ContentScale.None
            )
        }

        //  favourites icon
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            //  favourites
            CardButton(
                icon = if (detailsViewModel.isFavoriteToggled.value)
                    Icons.Filled.Favorite
                else
                    Icons.Outlined.FavoriteBorder,
                primaryColor = primaryColor,
                onClick = {
                    //  add item to favourites list
                    onFavoriteClicked()
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            //  call button
            CardButton(
                icon = Icons.Outlined.Call,
                primaryColor = primaryColor,
                onClick = {}
            )

        }

    }

}





























