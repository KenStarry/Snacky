package com.kenstarry.snacky.feature_home.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_home.domain.repository.HomeRepository

class GetSnacks(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(
        snacks: (snacks: List<Snack>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getSnacks(snacks, response)
}