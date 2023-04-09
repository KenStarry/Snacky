package com.kenstarry.snacky.feature_categories.domain.repository

import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack

interface CategoryRepository {

    suspend fun getSnacksInCategory(
        category: String,
        snacks: (snacksList: List<Snack>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    )

    suspend fun getCategoryDetails(
        category: String,
        categoryDetails: (details: Category) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    )
}