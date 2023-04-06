package com.kenstarry.snacky.feature_authentication.login.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_authentication.login.domain.repository.LoginRepository

class Login(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        onResponse: (res: Response<*>) -> Unit
    ) = repository.loginUser(email, password, onResponse)
}