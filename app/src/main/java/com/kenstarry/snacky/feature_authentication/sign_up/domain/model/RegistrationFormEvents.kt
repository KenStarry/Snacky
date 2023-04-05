package com.kenstarry.snacky.feature_authentication.sign_up.domain.model

sealed class RegistrationFormEvents {

    data class OnUsernameChanged(val username: String) : RegistrationFormEvents()

    data class OnEmailChanged(val email: String) : RegistrationFormEvents()

    data class OnPasswordChanged(val password: String) : RegistrationFormEvents()

    data class OnConfirmPasswordChanged(val confirmPass: String) : RegistrationFormEvents()

    object Submit : RegistrationFormEvents()
}
