package com.kenstarry.snacky.feature_settings.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_settings.domain.repository.SettingsRepository

class DeleteAccount(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(
        email: String,
        onResponse: (response: Response<*>) -> Unit
    ) = repository.deleteAccount(email, onResponse)
}