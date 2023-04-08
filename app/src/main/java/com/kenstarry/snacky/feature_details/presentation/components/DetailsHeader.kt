package com.kenstarry.snacky.feature_details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.DashboardCustomize
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.domain.model.SnackNameModel
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun DetailsHeader(
    snackName: SnackNameModel,
    snackCategory: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {

        Text(
            text = snackName.title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            maxLines = Int.MAX_VALUE,
            softWrap = true
        )

        Text(
            text = snackName.subTitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
            maxLines = Int.MAX_VALUE,
            softWrap = true
        )

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(MaterialTheme.spacing.extraLarge))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.DashboardCustomize,
                contentDescription = "group icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(MaterialTheme.spacing.medium)
            )

            Text(
                text = snackCategory,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

    }

}






































