package com.kenstarry.snacky.core.domain.use_case

import com.kenstarry.snacky.core.data.repository.CoreRepositoryImpl
import com.kenstarry.snacky.core.domain.repository.CoreRepository

class IsLoggedIn(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(): Boolean = repository.isUserLoggedIn()
}