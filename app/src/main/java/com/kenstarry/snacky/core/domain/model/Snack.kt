package com.kenstarry.snacky.core.domain.model

data class Snack(
    val snackName: SnackNameModel,
    val snackImageUrl: String,
    val snackDescription: String,
    val snackCalories: String,
    val snackPrice: Int,
    val snackPreparationTime: String,
    val snackRating: String,
    val snackCategory: String
) {
    constructor(): this(SnackNameModel("", ""), "", "", "",
    0, "", "", "")
}
