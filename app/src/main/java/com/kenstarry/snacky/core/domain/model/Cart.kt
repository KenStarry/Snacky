package com.kenstarry.snacky.core.domain.model

data class Cart(
    val snackTitle: String,
    val snackCategory: String,
    val snackPrice: Int,
    val snackQuantity: Int,
    val snackTotalPrice: Int
) {
    constructor() : this("", "", 0, 0, 0)
}
