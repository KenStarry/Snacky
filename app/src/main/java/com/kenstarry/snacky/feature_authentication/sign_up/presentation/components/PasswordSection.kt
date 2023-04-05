package com.kenstarry.snacky.feature_authentication.sign_up.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kenstarry.snacky.core.presentation.components.CustomTextField
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.RegistrationFormEvents
import com.kenstarry.snacky.feature_authentication.sign_up.presentation.viewmodel.SignUpViewModel

@Composable
fun PasswordSection(
    modifier: Modifier = Modifier,
    signUpVM: SignUpViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //  password section
        CustomTextField(
            textFieldValue = signUpVM.formState.password,
            startIcon = Icons.Outlined.Key,
            endIcon = null,
            placeholder = "New Password",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            isPassword = true,
            onInput = {
                signUpVM.onFormEvent(RegistrationFormEvents.OnPasswordChanged(it))
            }
        )

        AnimatedVisibility(visible = signUpVM.formState.passwordError != null) {
            ErrorMessage(
                message = signUpVM.formState.passwordError
            )
        }
    }

}