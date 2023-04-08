package com.kenstarry.snacky.feature_cart.domain.model

sealed class CartEvents {

    data class SetSubTotal(
        val priceToSet: Int
    ) : CartEvents()

    data class UpdateSubTotal(
        val priceToAdd: Int,
        val isAdd: Boolean
    ) : CartEvents()
}
