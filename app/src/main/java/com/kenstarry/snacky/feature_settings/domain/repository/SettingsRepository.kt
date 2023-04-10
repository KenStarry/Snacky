package com.kenstarry.snacky.feature_settings.domain.repository

import com.kenstarry.snacky.core.domain.model.Response

interface SettingsRepository {

    suspend fun logout(
        onResponse: (response: Response<*>) -> Unit
    )

    suspend fun deleteAccount(
        email: String,
        onResponse: (response: Response<*>) -> Unit
    )
}