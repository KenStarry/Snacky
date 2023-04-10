package com.kenstarry.snacky.feature_settings.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.feature_settings.data.datastore.ThemePreference
import com.kenstarry.snacky.feature_settings.data.repository.SettingsRepositoryImpl
import com.kenstarry.snacky.feature_settings.domain.repository.SettingsRepository
import com.kenstarry.snacky.feature_settings.domain.use_case.DeleteAccount
import com.kenstarry.snacky.feature_settings.domain.use_case.Logout
import com.kenstarry.snacky.feature_settings.domain.use_case.SettingsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    //  provide theme preference
    @Provides
    @Singleton
    fun provideThemePreference(
        @ApplicationContext context: Context
    ) = ThemePreference(context)

    //  provide settings repository
    @Provides
    @Singleton
    fun provideSettingsRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): SettingsRepository = SettingsRepositoryImpl(auth, db)

    //  provide settings use case
    @Provides
    @Singleton
    fun provideSettingsUseCases(
        repository: SettingsRepository
    ) = SettingsUseCases(
        logout = Logout(repository),
        deleteAccount = DeleteAccount(repository)
    )
}
























