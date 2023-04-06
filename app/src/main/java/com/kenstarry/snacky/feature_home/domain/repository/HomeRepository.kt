package com.kenstarry.snacky.feature_home.domain.repository

import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack

interface HomeRepository {

    suspend fun getSnackCategories(
        categories: (categories: List<Category>) -> Unit,
        response: (response: Response<*>) -> Unit
    )

    suspend fun getSnacks(
        snacks: (snacks: List<Snack>) -> Unit,
        response: (response: Response<*>) -> Unit
    )

}