package com.kenstarry.snacky.feature_settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.feature_settings.domain.model.SettingsEvents
import com.kenstarry.snacky.feature_settings.data.datastore.ThemePreference
import com.kenstarry.snacky.feature_settings.domain.use_case.SettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themePreference: ThemePreference,
    private val useCases: SettingsUseCases
) : ViewModel() {

    val themeFlow: Flow<String?> get() = themePreference.getTheme

    fun onEvent(event: SettingsEvents) {
        when (event) {
            is SettingsEvents.SetTheme -> {
                viewModelScope.launch {
                    themePreference.setTheme(theme = event.theme)
                }
            }

            is SettingsEvents.Logout -> {
                viewModelScope.launch {
                    useCases.logout(
                        onResponse = event.onResponse
                    )
                }
            }

            is SettingsEvents.DeleteAccount -> {
                viewModelScope.launch {
                    useCases.deleteAccount(
                        email = event.email,
                        onResponse = event.onResponse
                    )
                }
            }
        }
    }
}


































