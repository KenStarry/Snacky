package com.kenstarry.snacky.feature_settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.feature_settings.domain.model.SettingsEvents
import com.kenstarry.snacky.feature_settings.data.datastore.ThemePreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themePreference: ThemePreference
) : ViewModel() {

    val themeFlow: Flow<String?> get() = themePreference.getTheme

    fun onEvent(event: SettingsEvents) {
        when (event) {
            is SettingsEvents.SetTheme -> {
                viewModelScope.launch {
                    themePreference.setTheme(theme = event.theme)
                }
            }
        }
    }
}