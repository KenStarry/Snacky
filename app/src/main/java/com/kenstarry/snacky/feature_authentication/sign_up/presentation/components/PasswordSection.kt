package com.kenstarry.snacky.feature_authentication.sign_up.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kenstarry.snacky.core.presentation.components.CustomTextField

@Composable
fun PasswordSection(
    modifier: Modifier = Modifier
) {

    //  Username section
    CustomTextField(
        startIcon = Icons.Outlined.Key,
        endIcon = null,
        placeholder = "New Password",
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Password,
        primaryColor = MaterialTheme.colorScheme.primary,
        tertiaryColor = MaterialTheme.colorScheme.tertiary,
        isPassword = true,
        onInput = {}
    )

}