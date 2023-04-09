package com.kenstarry.snacky.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun CenterBackTopBar(
    title: String,
    titleIcon: ImageVector,
    vararg actions: ImageVector,
    onBackPressed: () -> Unit,
    onActionIconPressed: (icon: ImageVector) -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = titleIcon,
                    contentDescription = "center icon",
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "arrow back icon"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
        ),

        actions = {
            actions.forEach { actionIcon ->
                IconButton(onClick = { onActionIconPressed(actionIcon) }) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = "Search icon"
                    )
                }
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(MaterialTheme.spacing.small),

        )
}