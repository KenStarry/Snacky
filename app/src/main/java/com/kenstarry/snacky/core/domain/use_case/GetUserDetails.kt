package com.kenstarry.snacky.core.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.core.domain.repository.CoreRepository

class GetUserDetails(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(
        email: String,
        user: (user: User?) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) = repository.getUserDetails(email, user, onResponse)
}