package com.kenstarry.snacky.feature_cart.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.feature_details.domain.use_case.DetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCases: DetailsUseCases
) : ViewModel() {

    private val _cartItems = mutableStateOf<List<Cart>>(emptyList())
    val cartItems: State<List<Cart>> = _cartItems
}