package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.validation

import com.kenstarry.snacky.feature_authentication.AuthConstants
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation.ValidationResult

class ValidateUserName {

    fun execute(
        userName: String
    ) : ValidationResult {

        val containsDigitsOnly = userName.all { char ->
            char.isDigit()
        }

        if (userName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_USERNAME_ERROR
            )
        }

        if (userName.length < 2) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.SHORT_USERNAME_ERROR
            )
        }

        if (containsDigitsOnly) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.DIGITS_ONLY_USERNAME_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}




















