package com.kenstarry.snacky.feature_settings.domain.use_case

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_settings.domain.repository.SettingsRepository

class Logout(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(
        onResponse: (response: Response<*>) -> Unit
    ) = repository.logout(onResponse)
}