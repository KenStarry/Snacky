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
fun ConfirmPasswordSection(
    modifier: Modifier = Modifier,
    signUpVM: SignUpViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  confirm password section
        CustomTextField(
            textFieldValue = signUpVM.formState.confirmPassword,
            startIcon = Icons.Outlined.Key,
            endIcon = null,
            placeholder = "Confirm Password",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            isPassword = true,
            onInput = {
                signUpVM.onFormEvent(RegistrationFormEvents.OnConfirmPasswordChanged(it))
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        AnimatedVisibility(visible = signUpVM.formState.confirmPasswordError != null) {
            ErrorMessage(
                message = signUpVM.formState.confirmPasswordError
            )
        }

    }


}