package com.kenstarry.snacky.feature_authentication.login.domain.repository

import com.kenstarry.snacky.core.domain.model.Response

interface LoginRepository {

    //  login user with email and password
    suspend fun loginUser(
        email: String,
        password: String,
        onResponse: (res: Response<*>) -> Unit
    )
}