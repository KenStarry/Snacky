package com.kenstarry.snacky.feature_authentication.login.domain.model.form

sealed class LoginFormEvents {

    data class OnLoginEmailChanged(val email: String) : LoginFormEvents()

    data class OnLoginPasswordChanged(val password: String) : LoginFormEvents()
}
