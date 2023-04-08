package com.kenstarry.snacky.core.domain.model

data class Cart(
    val snack: Snack,
    val snackQuantity: Int,
    val snackTotalPrice: Int
) {
    constructor() : this(Snack(), 0, 0)
}
