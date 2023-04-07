package com.kenstarry.snacky.feature_details.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardButton(
    icon: ImageVector,
    primaryColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(45.dp)
            .shadow(
                ambientColor = primaryColor,
                spotColor = primaryColor,
                elevation = MaterialTheme.spacing.large
            ),
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = "Card icon",
                tint = primaryColor
            )

        }

    }
}