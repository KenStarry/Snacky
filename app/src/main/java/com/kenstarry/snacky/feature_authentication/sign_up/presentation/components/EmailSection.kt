package com.kenstarry.snacky.feature_authentication.sign_up.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kenstarry.snacky.core.presentation.components.CustomTextField

@Composable
fun EmailSection(
    modifier: Modifier = Modifier
) {

    //  Username section
    CustomTextField(
        startIcon = Icons.Outlined.AlternateEmail,
        endIcon = null,
        placeholder = "Email Address",
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email,
        primaryColor = MaterialTheme.colorScheme.primary,
        tertiaryColor = MaterialTheme.colorScheme.tertiary,
        onInput = {}
    )

}