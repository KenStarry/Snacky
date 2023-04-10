package com.kenstarry.snacky.feature_settings.domain.model

sealed class SettingsEvents {

    data class SetTheme(val theme: String) : SettingsEvents()
}
