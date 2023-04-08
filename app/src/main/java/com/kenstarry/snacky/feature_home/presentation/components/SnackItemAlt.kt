package com.kenstarry.snacky.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
fun SnackItemAlt(
    modifier: Modifier = Modifier,
    context: Context,
    snack: Snack,
    isFavourite: Boolean,
    onFavoriteClicked: () -> Unit,
    onSnackClicked: () -> Unit
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onSnackClicked() },
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  image
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {

                CoilImage(
                    context = context,
                    imageUri = snack.snackImageUrl.toUri(),
                    placeholder = R.drawable.undraw_ice_cream_s_2_rh,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Fit
                )

            }

            //  content
            Column(
                modifier = Modifier
                    .weight(2f)
                    .wrapContentHeight()
                    .padding(MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                //  snack name
                Text(
                    text = snack.snackName.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                //  snack description
                Text(
                    text = snack.snackName.subTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = "timer",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(MaterialTheme.spacing.medium)
                    )

                    Text(
                        text = snack.snackPreparationTime,
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

                    IconButton(onClick = onFavoriteClicked) {
                        Icon(
                            imageVector = if (isFavourite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,
                            contentDescription = "favourite icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            }

        }
    }

}


























