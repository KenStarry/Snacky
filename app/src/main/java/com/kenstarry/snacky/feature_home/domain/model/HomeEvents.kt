package com.kenstarry.snacky.feature_home.domain.model

import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response

sealed class HomeEvents {

    data class GetCategories(
        val response: (response: Response<*>) -> Unit
    ) : HomeEvents()
}
