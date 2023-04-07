package com.kenstarry.snacky.feature_details.domain.repository

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack

interface DetailsRepository {

    suspend fun getSnack(
        snack: (snack: Snack) -> Unit,
        response: (response: Response<*>) -> Unit
    )
}