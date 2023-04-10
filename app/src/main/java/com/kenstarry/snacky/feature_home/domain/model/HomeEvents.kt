package com.kenstarry.snacky.feature_home.domain.model

import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack

sealed class HomeEvents {

    data class GetCategories(
        val response: (response: Response<*>) -> Unit
    ) : HomeEvents()

    data class GetSnacks(
        val response: (response: Response<*>) -> Unit
    ) : HomeEvents()

    data class SearchForSnacks(
        val query: String,
        val filteredSnacks: (snacks: List<Snack>) -> Unit
    ) :  HomeEvents()
}
