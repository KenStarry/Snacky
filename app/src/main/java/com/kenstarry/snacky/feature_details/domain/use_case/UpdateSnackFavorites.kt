package com.kenstarry.snacky.feature_details.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_details.domain.repository.DetailsRepository

class UpdateSnackFavorites(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(
        email: String,
        snack: Snack,
        isAdd: Boolean,
        response: (response: Response<*>) -> Unit
    ) = repository.updateSnackFavorites(email, snack, isAdd, response)
}