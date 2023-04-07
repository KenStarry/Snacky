package com.kenstarry.snacky.feature_details.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.presentation.components.CoilImage
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsImage(
    context: Context,
    snackImage: String,
    primaryColor: Color
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
                icon = Icons.Outlined.FavoriteBorder,
                primaryColor = primaryColor,
                onClick = {}
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





























