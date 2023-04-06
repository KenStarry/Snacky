package com.kenstarry.snacky.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Filter
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kenstarry.snacky.core.presentation.components.CustomTextField
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun SearchSection() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(5f)
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            //  search text field
            CustomTextField(
                startIcon = Icons.Outlined.Search,
                endIcon = null,
                placeholder = "Burgers, milkshakes etc.",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                onInput = {}
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            //  filter button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                    .size(45.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Outlined.Filter,
                    contentDescription = "Filter icon",
                    tint = Color.White
                )

            }
        }

    }
}
































