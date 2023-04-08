package com.kenstarry.snacky.feature_cart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kenstarry.snacky.core.presentation.components.CustomTextField
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun CartPromoCode() {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(MaterialTheme.spacing.large))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CustomTextField(
            startIcon = Icons.Outlined.Code,
            containerColor = MaterialTheme.colorScheme.onSecondary,
            endIcon = null,
            placeholder = "Promo code",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            onInput = {

            },
            modifier = Modifier
                .weight(1f)
        )

        //  activate promo code button
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Apply",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}



































