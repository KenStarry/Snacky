package com.kenstarry.snacky.feature_cart.presentation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.kenstarry.snacky.feature_home.presentation.viewmodel.HomeViewModel
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun CartItemsSection(
    cartItems: List<Cart>
) {

    val listState = rememberLazyListState()
    val detailsVM: DetailsViewModel = hiltViewModel()
    val cartVM: CartViewModel = viewModel()

    var total = 0

    cartItems.forEach {
        total += it.snackTotalPrice

        Log.d("cart", total.toString())

        cartVM.onEvent(
            CartEvents.SetSubTotal(
                priceToSet = total
            ))
    }

    LazyColumn(
        content = {
            items(cartItems) { cart ->

                SnackCartItem(
                    cart = cart,
                    onSnackClicked = {}
                )
            }
        },
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    )
}






































