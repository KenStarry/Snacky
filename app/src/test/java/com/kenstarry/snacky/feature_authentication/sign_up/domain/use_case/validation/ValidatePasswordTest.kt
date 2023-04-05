package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.validation

import com.google.common.truth.Truth.assertThat
import com.kenstarry.snacky.feature_authentication.AuthConstants
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation.ValidationResult
import org.junit.Before
import org.junit.Test

class ValidatePasswordTest {

    private lateinit var validatePassword: ValidatePassword

    @Before
    fun setup() {
        validatePassword = ValidatePassword()
    }

    @Test
    fun `blank password returns false`() {

        val result = validatePassword.execute("")

        assertThat(result).isEqualTo(
            ValidationResult(true, AuthConstants.BLANK_PASSWORD_ERROR)
        )

    }
}