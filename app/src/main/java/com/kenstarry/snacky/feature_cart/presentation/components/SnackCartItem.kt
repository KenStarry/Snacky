package com.kenstarry.snacky.feature_cart.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.core.presentation.components.CoilImage
import com.kenstarry.snacky.feature_details.presentation.components.CardButton
import com.kenstarry.snacky.ui.custom.spacing
import com.kenstarry.snacky.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackCartItem(
    modifier: Modifier = Modifier,
    cart: Cart,
    onSnackClicked: () -> Unit
) {

    val context = LocalContext.current

    cart.let {

        var quantity by remember {
            mutableStateOf(it.snackQuantity)
        }

        var totalPrice by remember {
            mutableStateOf(it.snackTotalPrice)
        }

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
                        imageUri = it.snack.snackImageUrl.toUri(),
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        //  snack name
                        Text(
                            text = it.snack.snackName.title,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .weight(2f)
                        )

                        //  total price to pay
                        Text(
                            text = "ksh. $totalPrice",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .weight(1f)
                        )

                    }

                    //  snack description
                    Text(
                        text = it.snack.snackDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

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
                                append(it.snack.snackPrice.toString())
                            }
                        })

                        //  counter buttons
                        Row(
                            modifier = Modifier
                                .wrapContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            //  minus button
                            CardButton(
                                size = 35.dp,
                                icon = Icons.Outlined.Minimize,
                                containerColor = MaterialTheme.colorScheme.primary,
                                primaryColor = Color.White,
                                onClick = {
                                    quantity -= 1
                                }
                            )

                            //  counter
                            Text(
                                text = quantity.toString(),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                            )

                            //  add button
                            CardButton(
                                size = 35.dp,
                                icon = Icons.Outlined.Add,
                                containerColor = MaterialTheme.colorScheme.primary,
                                primaryColor = Color.White,
                                onClick = {
                                    quantity += 1
                                }
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                }

            }
        }
    }

}


























