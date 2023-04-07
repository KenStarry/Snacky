package com.kenstarry.snacky.feature_details.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun DetailsTopBar(
    onBackPressed: () -> Unit
) {

    SmallTopAppBar(
        title = {
            Text(
                text = "",
                style = MaterialTheme.typography.bodyLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back arrow"
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
        )
    )

}