package com.kenstarry.snacky.feature_details.domain.model

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack

sealed class DetailsEvents {

    data class GetSnack(
        val categoryTitle: String,
        val snackTitle: String,
        val response: (response: Response<*>) -> Unit
    ) : DetailsEvents()
}
