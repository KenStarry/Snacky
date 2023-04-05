package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.feature_authentication.sign_up.domain.repository.SignUpRepository

class CreateAccount(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(
        user: User,
        response: (onResponse: Response<*>) -> Unit
    ) = signUpRepository.createAccount(user, response)
}