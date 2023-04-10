package com.kenstarry.snacky.feature_cart.presentation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_cart.domain.model.CartEvents
import com.kenstarry.snacky.feature_cart.presentation.viewmodel.CartViewModel
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.feature_home.domain.model.HomeEvents
import com.kenstarry.snacky.feature_home.presentation.components.SnackItem
import com.kenstarry.snacky.feature_home.presentation.viewmodel.HomeViewModel
import com.kenstarry.snacky.ui.custom.spacing
import com.kenstarry.snacky.window.model.WindowType
import com.kenstarry.snacky.window.rememberWindowInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartItemsSection(
    cartItems: List<Cart>,
    email: String
) {

    val listState = rememberLazyListState()
    val detailsVM: DetailsViewModel = hiltViewModel()
    val context = LocalContext.current
    val cartVM: CartViewModel = viewModel()
    val windowInfo = rememberWindowInfo()

    var total = 0

    cartItems.forEach {
        total += it.snackTotalPrice

        Log.d("cart", total.toString())

        cartVM.onEvent(
            CartEvents.SetSubTotal(
                priceToSet = total
            )
        )
    }

    if (windowInfo.screenWidthInfo is WindowType.Compact) {
        LazyColumn(
            content = {
                items(cartItems) { cart ->

                    SnackCartItem(
                        cart = cart,
                        onSnackClicked = {},
                        onRemoveFromCart = {
                            detailsVM.onEvent(DetailsEvents.UpdateCartItems(
                                email = email,
                                cart = cart,
                                isAdd = false,
                                response = { res ->
                                    when (res) {
                                        is Response.Success -> {
                                            Toast.makeText(
                                                context,
                                                "Item removed successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                        is Response.Failure -> {
                                            Toast.makeText(
                                                context,
                                                "something went wrong",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            ))
                        }
                    )
                }
            },
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (cartItems.size < 2) 200.dp else 350.dp),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        )
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(300.dp),
            content = {
                items(cartItems) { cart ->

                    SnackCartItem(
                        cart = cart,
                        onSnackClicked = {},
                        onRemoveFromCart = {
                            detailsVM.onEvent(DetailsEvents.UpdateCartItems(
                                email = email,
                                cart = cart,
                                isAdd = false,
                                response = { res ->
                                    when (res) {
                                        is Response.Success -> {
                                            Toast.makeText(
                                                context,
                                                "Item removed successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                        is Response.Failure -> {
                                            Toast.makeText(
                                                context,
                                                "something went wrong",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            ))
                        }
                    )
                }
            },
            state = rememberLazyStaggeredGridState(),
            modifier = Modifier
                .fillMaxWidth()
                .height(if (cartItems.size < 2) 250.dp else 500.dp),
            verticalItemSpacing = MaterialTheme.spacing.large,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
        )
    }
}






































