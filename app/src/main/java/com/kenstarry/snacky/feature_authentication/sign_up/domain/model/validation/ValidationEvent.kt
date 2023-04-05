package com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation

sealed class ValidationEvent {
    object Success : ValidationEvent()
    object Failure : ValidationEvent()
}
