package com.kenstarry.snacky.core.domain.model

data class User(
    val userImageUri: String,
    val userName: String,
    val userEmail: String,
    val userPassword: String,
    val userSnackFavourites: List<String>,
    val userSnackOrders: List<String>,
    val userCartItems: List<String>
) {
    constructor() : this("", "", "", "", emptyList(), emptyList(), emptyList())
}
