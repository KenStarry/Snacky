package com.kenstarry.snacky.feature_details.domain.model

import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack

sealed class DetailsEvents {

    data class GetSnack(
        val categoryTitle: String,
        val snackTitle: String,
        val response: (response: Response<*>) -> Unit
    ) : DetailsEvents()

    data class UpdateSnackFavorites(
        val email: String,
        val snackTitle: String,
        val isAdd: Boolean,
        val response: (response: Response<*>) -> Unit
    ) : DetailsEvents()

    data class UpdateCartItems(
        val email: String,
        val cart: Cart,
        val isAdd: Boolean,
        val response: (response: Response<*>) -> Unit
    ) : DetailsEvents()

    data class ToggleFavoritesButton(
        val isFavoriteClicked: Boolean
    ) : DetailsEvents()

    data class UpdateItemsCount(
        val isAdd: Boolean
    ) : DetailsEvents()
}
