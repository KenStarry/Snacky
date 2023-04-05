package com.kenstarry.snacky.feature_authentication.sign_up.domain.model

import android.net.Uri

data class RegistrationFormState(
    val imageUri: Uri? = null,
    val email: String = "",
    val emailError: String? = null,
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null
)
