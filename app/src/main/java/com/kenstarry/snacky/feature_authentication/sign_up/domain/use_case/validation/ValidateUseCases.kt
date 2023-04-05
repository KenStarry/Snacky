package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.validation

data class ValidateUseCases(
    val validateEmail: ValidateEmail,
    val validateUserName: ValidateUserName,
    val validatePassword: ValidatePassword,
    val validateConfirmPassword: ValidateConfirmPassword
)
