package com.kenstarry.snacky.feature_categories.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_categories.domain.repository.CategoryRepository

class GetSnacksInCategory(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(
        category: String,
        snacks: (snacksList: List<Snack>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) = repository.getSnacksInCategory(category, snacks, onResponse)
}