package com.kenstarry.snacky.feature_cart.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.components.CenterBackTopBar
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
            CenterBackTopBar(
                title = "My Cart",
                titleIcon = Icons.Filled.ShoppingCart,
                actions = arrayOf(Icons.Outlined.MoreVert),
                onActionIconPressed = {},
                onBackPressed = {}
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            coreVM.userDetails.value?.let { user ->

                AnimatedVisibility(visible = user.userCartItems.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimary)
                            .verticalScroll(rememberScrollState())
                            .padding(MaterialTheme.spacing.medium),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                    ) {

                        //  title
                        Text(
                            text = "${user.userCartItems.size} snacks",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        //  cart items
                        CartItemsSection(
                            cartItems = user.userCartItems,
                            email = user.userEmail
                        )

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

                AnimatedVisibility(visible = user.userCartItems.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.undraw_shopping_app_flsj),
                            contentDescription = "Empty cart",
                            modifier = Modifier
                                .fillMaxSize(0.6f)
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                        Text(
                            text = "No items added to cart...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                        )

                    }
                }
            }

        }

    }

}




























