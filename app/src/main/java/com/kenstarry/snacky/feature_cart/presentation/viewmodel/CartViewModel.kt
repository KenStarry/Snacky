package com.kenstarry.snacky.feature_cart.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.feature_cart.domain.model.CartEvents
import dagger.hilt.android.lifecycle.HiltViewModel

class CartViewModel() : ViewModel() {

    private val _cartItems = mutableStateOf<List<Cart>>(emptyList())
    val cartItems: State<List<Cart>> = _cartItems

    private val _subTotalPrice = mutableStateOf<Int>(0)
    val subTotalPrice: State<Int> = _subTotalPrice

    fun onEvent(event: CartEvents) {
        when (event) {

            is CartEvents.SetSubTotal -> {
                _subTotalPrice.value = event.priceToSet
            }

            is CartEvents.UpdateSubTotal -> {
                if (event.isAdd){
                    _subTotalPrice.value += event.priceToAdd
                } else {
                    _subTotalPrice.value -= event.priceToAdd
                }
            }
        }
    }
}