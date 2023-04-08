package com.kenstarry.snacky.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.presentation.components.CoilImage
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun HomeTopBar(
    context: Context,
    userName: String,
    imageUri: String?,
    onSearchPressed: () -> Unit,
    onImageClicked: () -> Unit
) {

    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  waving hand
                Image(
                    painter = painterResource(id = R.drawable.wave),
                    contentDescription = "Waving hand",
                    modifier = Modifier
                        .size(MaterialTheme.spacing.large),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                Text(
                    text = "Hi, $userName",
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        },
        navigationIcon = {
            //  user image
            CoilImage(
                context = context,
                imageUri = imageUri?.toUri(),
                placeholder = R.drawable.profile,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable { onImageClicked() }
            )
        },
        actions = {
            IconButton(onClick = onSearchPressed) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search icon"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
        ),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(MaterialTheme.spacing.small),

        )

}