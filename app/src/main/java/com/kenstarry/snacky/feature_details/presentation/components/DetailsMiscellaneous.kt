package com.kenstarry.snacky.feature_details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DashboardCustomize
import androidx.compose.material.icons.outlined.Fireplace
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsMiscellaneous(
    snack: Snack
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  ratings
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(MaterialTheme.spacing.extraLarge))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.StarRate,
                contentDescription = "group icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(MaterialTheme.spacing.medium)
            )

            Text(
                text = snack.snackRating,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

        //  prepare time
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(MaterialTheme.spacing.extraLarge))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.Timer,
                contentDescription = "group icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(MaterialTheme.spacing.medium)
            )

            Text(
                text = "prep: ${snack.snackPreparationTime}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

        //  calories
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(MaterialTheme.spacing.extraLarge))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.onSecondary)
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.Fireplace,
                contentDescription = "fire icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(MaterialTheme.spacing.medium)
            )

            Text(
                text = "calories: ${snack.snackCalories}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }

    }

}