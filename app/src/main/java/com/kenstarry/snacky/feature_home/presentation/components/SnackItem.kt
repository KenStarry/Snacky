package com.kenstarry.snacky.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Fireplace
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.presentation.components.CoilImage
import com.kenstarry.snacky.ui.custom.spacing
import com.kenstarry.snacky.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackItem(
    context: Context,
    snack: Snack
) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(270.dp),
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
            ) {

                //  snack title
                Text(
                    text = snack.snackName.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                //  snack sub title
                Text(
                    text = snack.snackName.subTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.4f)
                )

            }

            //  image
            CoilImage(
                context = context,
                imageUri = snack.snackImageUrl.toUri(),
                placeholder = R.drawable.undraw_ice_cream_s_2_rh,
                contentScale = ContentScale.None,
                modifier = Modifier
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                    .fillMaxWidth()
                    .height(100.dp)
            )

            //  calories
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Fireplace,
                    contentDescription = "Calories",
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "${snack.snackCalories} calories",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )

            }

            //  pricing
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            fontFamily = Poppins,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("Ksh. ")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontFamily = Poppins,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                        )
                    ) {
                        append(snack.snackPrice.toString())
                    }
                })

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                        .size(45.dp)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Add icon",
                        tint = Color.White
                    )

                }

            }

        }
    }

}































