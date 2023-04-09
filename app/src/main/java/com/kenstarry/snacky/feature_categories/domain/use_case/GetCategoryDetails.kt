package com.kenstarry.snacky.feature_categories.domain.use_case

import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_categories.domain.repository.CategoryRepository

class GetCategoryDetails(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(
        category: String,
        categoryDetails: (details: Category) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) = repository.getCategoryDetails(category, categoryDetails, onResponse)
}