package com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
