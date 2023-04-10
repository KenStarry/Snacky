package com.kenstarry.snacky.feature_settings.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.kenstarry.snacky.feature_settings.domain.model.SettingsSectionItem

object SettingsConstants {

    const val LOGOUT_DIALOG = "logout alert dialog"
    const val DELETE_ACCOUNT_DIALOG = "delete account alert dialog"

    //  theme section
    val themeOptions = listOf(
        SettingsSectionItem("Dark Theme", Icons.Outlined.Nightlight),
        SettingsSectionItem("Light Theme", Icons.Outlined.LightMode),
        SettingsSectionItem("Follow System", Icons.Outlined.Light)
    )

    //  logout section
    val accountOptions = listOf(
        SettingsSectionItem("Logout", Icons.Outlined.Logout),
        SettingsSectionItem("Delete Account", Icons.Outlined.DeleteForever)
    )
}