package com.kenstarry.snacky.feature_details.presentation

import android.graphics.Color.parseColor
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.components.BackPressTopBar
import com.kenstarry.snacky.core.presentation.utils.PaletteGenerator
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.components.*
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    mainNavHostController: NavHostController,
    snackCategory: String,
    snackTitle: String
) {
    val systemController = rememberSystemUiController()

    val direction = Direction(mainNavHostController)
    val coreVM: CoreViewModel = hiltViewModel()
    val detailsVM: DetailsViewModel = hiltViewModel()
    val context = LocalContext.current

    val currentUser = coreVM.getCurrentUser()
    coreVM.onEvent(CoreEvents.GetUserDetails(
        email = currentUser?.email ?: "no email",
        onResponse = {}
    ))

    //  get snack
    detailsVM.onEvent(DetailsEvents.GetSnack(
        categoryTitle = snackCategory,
        snackTitle = snackTitle,
        response = {
            when (it) {
                is Response.Success -> {
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                }

                is Response.Failure -> {
                    Toast.makeText(context, "could not retrieve details.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    ))

    detailsVM.snack.value?.let { snack ->

        coreVM.userDetails.value?.let {
            //  set the favourites list
            if (it.userSnackFavourites.contains(snack)) {
                detailsVM.onEvent(DetailsEvents.ToggleFavoritesButton(true))
            }
        }

        Scaffold(
            topBar = {
                DetailsTopBar(
                    onBackPressed = {
                        direction.navigateUp()
                    }
                )
            }
        ) { contentPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .verticalScroll(rememberScrollState())
                        .padding(MaterialTheme.spacing.medium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                ) {

                    //  header
                    DetailsHeader(
                        snackName = snack.snackName,
                        snackCategory = snackCategory
                    )

                    //  image
                    DetailsImage(
                        context = context,
                        detailsViewModel = detailsVM,
                        snackImage = snack.snackImageUrl,
                        primaryColor = MaterialTheme.colorScheme.primary,
                        onFavoriteClicked = {

                            detailsVM.onEvent(
                                DetailsEvents.ToggleFavoritesButton(
                                    !detailsVM.isFavoriteToggled.value
                                )
                            )

                            //  add item to update list
                            currentUser?.email?.let {
                                detailsVM.onEvent(DetailsEvents.UpdateSnackFavorites(
                                    email = it,
                                    snack = snack,
                                    isAdd = detailsVM.isFavoriteToggled.value,
                                    response = { res ->
                                        when (res) {
                                            is Response.Success -> {
                                                if (detailsVM.isFavoriteToggled.value) {
                                                    Toast.makeText(
                                                        context,
                                                        "Snack added to favourites successfully!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Snack removed from favourites successfully!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                            is Response.Failure -> {
                                                Toast.makeText(
                                                    context,
                                                    res.error.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                ))
                            }
                        }
                    )

                    //  price
                    DetailsPrice(
                        detailsVM = detailsVM,
                        snackPrice = snack.snackPrice,
                        primaryColor = MaterialTheme.colorScheme.primary
                    )

                    //  more details
                    DetailsMiscellaneous(snack = snack)

                    //  description
                    DetailsDescription(snackDescription = snack.snackDescription)
                    
                    Spacer(modifier = Modifier.height(64.dp))

                }

                //  Add to cart button
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.medium),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {

                    val myCart = Cart(
                        snack = snack,
                        snackQuantity = detailsVM.itemQuantity.value,
                        snackTotalPrice = snack.snackPrice * detailsVM.itemQuantity.value
                    )

                    coreVM.userDetails.value?.let { userDetails ->

                        if (userDetails.userCartItems.map { it.snack.snackName.title }
                                .contains(myCart.snack.snackName.title)) {

                            Icon(
                                imageVector = Icons.Outlined.Done,
                                contentDescription = "done icon",
                                tint = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Item already added to cart.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )

                        } else {
                            //  add to cart button
                            Button(
                                onClick = {
                                    if (detailsVM.itemQuantity.value > 0) {
                                        //    add my cart item in firestore
                                        detailsVM.onEvent(
                                            DetailsEvents.UpdateCartItems(
                                                email = userDetails.userEmail,
                                                cart = Cart(
                                                    snack = snack,
                                                    snackQuantity = detailsVM.itemQuantity.value,
                                                    snackTotalPrice = snack.snackPrice * detailsVM.itemQuantity.value
                                                ),
                                                isAdd = true,
                                                response = {}
                                            )
                                        )
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please select a quantity",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(

                                    containerColor = if (detailsVM.itemQuantity.value > 0)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        Color.Gray,

                                    contentColor = if (detailsVM.itemQuantity.value > 0)
                                        Color.White
                                    else
                                        MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentPadding = PaddingValues(MaterialTheme.spacing.medium)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ShoppingCart,
                                    contentDescription = "cart icon",
                                    tint = if (detailsVM.itemQuantity.value > 0)
                                        Color.White
                                    else
                                        MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                )

                                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                                Text(
                                    text = "Add To Cart",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (detailsVM.itemQuantity.value > 0)
                                        Color.White
                                    else
                                        MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                )
                            }
                        }
                    }
                }

            }

        }
    }

}