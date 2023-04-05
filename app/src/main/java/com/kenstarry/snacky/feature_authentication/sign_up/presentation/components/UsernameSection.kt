package com.kenstarry.snacky.feature_authentication.sign_up.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
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
fun UsernameSection(
    modifier: Modifier = Modifier,
    signUpVM: SignUpViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //  Username section
        CustomTextField(
            textFieldValue = signUpVM.formState.username,
            startIcon = Icons.Outlined.VerifiedUser,
            endIcon = null,
            placeholder = "Username",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            onInput = {
                signUpVM.onFormEvent(RegistrationFormEvents.OnUsernameChanged(it))
            }
        )

        AnimatedVisibility(visible = signUpVM.formState.usernameError != null) {
            ErrorMessage(
                message = signUpVM.formState.usernameError
            )
        }
    }

}