package com.kenstarry.snacky.feature_details.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_details.domain.repository.DetailsRepository

class GetSnack(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(
        categoryTitle: String,
        snackTitle: String,
        snack: (snack: Snack) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getSnack(categoryTitle, snackTitle, snack, response)
}