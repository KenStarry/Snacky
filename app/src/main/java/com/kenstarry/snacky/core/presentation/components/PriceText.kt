package com.kenstarry.snacky.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.kenstarry.snacky.ui.theme.Poppins

@Composable
fun PriceText(
    modifier: Modifier = Modifier,
    price: String,
    priceSize: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    primaryColor: Color
) {

    Text(
        text = buildAnnotatedString {

            withStyle(
                style = SpanStyle(
                    fontFamily = Poppins,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    color = primaryColor
                )
            ) {
                append("Ksh. ")
            }

            withStyle(
                style = SpanStyle(
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                    fontSize = priceSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                )
            ) {
                append(price)
            }
        },
        modifier = modifier
    )
}