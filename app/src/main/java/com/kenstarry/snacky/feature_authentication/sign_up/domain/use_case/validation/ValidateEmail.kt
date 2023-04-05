package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.validation

import android.util.Patterns
import com.kenstarry.snacky.feature_authentication.AuthConstants
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation.ValidationResult

class ValidateEmail {

    fun execute(email: String): ValidationResult {

        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.BLANK_EMAIL_ERROR
            )
        }

        //  check if email is a valid email address
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = AuthConstants.INVALID_EMAIL_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}