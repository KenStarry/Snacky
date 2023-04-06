package com.kenstarry.snacky.feature_authentication.login.domain.model

import com.kenstarry.snacky.core.domain.model.Response

sealed class LoginEvents {

    data class LoginUser(
        val email: String,
        val password: String,
        val onResponse: (res: Response<*>) -> Unit
    ) : LoginEvents()
}
