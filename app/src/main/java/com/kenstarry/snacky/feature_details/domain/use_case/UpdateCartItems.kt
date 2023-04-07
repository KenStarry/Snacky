package com.kenstarry.snacky.feature_details.domain.use_case

import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_details.domain.repository.DetailsRepository

class UpdateCartItems(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(
        email: String,
        cart: Cart,
        isAdd: Boolean,
        response: (response: Response<*>) -> Unit
    ) = repository.updateCartItems(email, cart, isAdd, response)
}