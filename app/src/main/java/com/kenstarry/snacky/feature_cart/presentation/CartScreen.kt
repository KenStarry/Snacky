package com.kenstarry.snacky.feature_cart.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_cart.presentation.components.CartItemsSection
import com.kenstarry.snacky.feature_cart.presentation.components.CartPromoCode
import com.kenstarry.snacky.feature_cart.presentation.components.CartSummary
import com.kenstarry.snacky.feature_cart.presentation.components.CartTopBar
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    mainNavHostController: NavHostController,
    innerNavHostController: NavHostController,
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val currentUser = coreVM.getCurrentUser()
    coreVM.onEvent(CoreEvents.GetUserDetails(
        email = currentUser?.email ?: "no email",
        onResponse = {}
    ))

    Scaffold(
        topBar = {
            CartTopBar(title = "My Cart") {

            }
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .verticalScroll(rememberScrollState())
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
            ) {

                coreVM.userDetails.value?.let { user ->

                    //  cart items
                    CartItemsSection(cartItems = user.userCartItems)

                    //  promo code
                    CartPromoCode()

                    //  add to cart summary
                    CartSummary()

                    //  checkout button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = MaterialTheme.spacing.large,
                                    ambientColor = MaterialTheme.colorScheme.primary,
                                    spotColor = MaterialTheme.colorScheme.primary
                                ),
                            contentPadding = PaddingValues(MaterialTheme.spacing.medium)
                        ) {

                            Text(
                                text = "Proceed to checkout",
                                style = MaterialTheme.typography.bodyMedium
                            )

                        }

                    }
                }

            }

        }

    }

}




























