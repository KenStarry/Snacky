package com.kenstarry.snacky.feature_details.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kenstarry.snacky.core.presentation.components.PriceText
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun DetailsPrice(
    detailsVM: DetailsViewModel,
    snackPrice: Int,
    primaryColor: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  price
        Column(
            modifier = Modifier
                .weight(2f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {

            //  price per unit
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                PriceText(
                    price = snackPrice.toString(),
                    priceSize = MaterialTheme.typography.titleMedium.fontSize,
                    primaryColor = primaryColor
                )

                Row(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //  minus button
                    CardButton(
                        icon = Icons.Outlined.Minimize,
                        primaryColor = primaryColor,
                        onClick = {
                            detailsVM.onEvent(DetailsEvents.UpdateItemsCount(false))
                        }
                    )

                    //  counter
                    Text(
                        text = detailsVM.itemQuantity.value.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                    //  add button
                    CardButton(
                        icon = Icons.Outlined.Add,
                        primaryColor = primaryColor,
                        onClick = {
                            detailsVM.onEvent(DetailsEvents.UpdateItemsCount(true))
                        }
                    )

                }

            }

            //  total price
            Text(
                text = "Total Price: ksh. ${
                    if (detailsVM.itemQuantity.value > 0) {
                        detailsVM.itemQuantity.value * snackPrice
                    } else {
                        0.0
                    }
                }",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
            )

        }

    }

}





























