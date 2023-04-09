package com.kenstarry.snacky.feature_categories.domain.model

import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack

sealed class CategoryEvents {

    data class GetSnacksInCategory(
        val category: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : CategoryEvents()

    data class GetCategoryDetails(
        val category: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : CategoryEvents()
}
























