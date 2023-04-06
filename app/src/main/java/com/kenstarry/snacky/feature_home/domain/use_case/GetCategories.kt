package com.kenstarry.snacky.feature_home.domain.use_case

import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_home.domain.repository.HomeRepository

class GetCategories(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(
        categories: (categories: List<Category>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getSnackCategories(categories, response)
}