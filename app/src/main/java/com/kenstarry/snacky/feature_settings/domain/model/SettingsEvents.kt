package com.kenstarry.snacky.feature_settings.domain.model

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_settings.presentation.model.AlertDialogStatus

sealed class SettingsEvents {

    data class SetTheme(val theme: String) : SettingsEvents()

    data class ToggleAlertDialog(
        val alertDialogStatus: AlertDialogStatus
    ) : SettingsEvents()

    data class Logout(
        val onResponse: (response: Response<*>) -> Unit
    ) : SettingsEvents()

    data class DeleteAccount(
        val email: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : SettingsEvents()

}
