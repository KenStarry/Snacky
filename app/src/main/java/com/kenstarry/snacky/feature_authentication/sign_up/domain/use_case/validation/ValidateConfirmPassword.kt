package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.validation

import com.kenstarry.snacky.feature_authentication.AuthConstants
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation.ValidationResult

class ValidateConfirmPassword {

    fun execute(
        password: String,
        repeatedPassword: String
    ) : ValidationResult {

        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.PASSWORD_MATCH_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}