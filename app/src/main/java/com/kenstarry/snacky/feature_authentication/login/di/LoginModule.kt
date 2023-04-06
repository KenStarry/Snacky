package com.kenstarry.snacky.feature_authentication.login.di

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.kenstarry.snacky.feature_authentication.login.data.repository.LoginRepositoryImpl
import com.kenstarry.snacky.feature_authentication.login.domain.repository.LoginRepository
import com.kenstarry.snacky.feature_authentication.login.domain.use_case.Login
import com.kenstarry.snacky.feature_authentication.login.domain.use_case.LoginUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        auth: FirebaseAuth
    ): LoginRepository = LoginRepositoryImpl(auth)

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: LoginRepository
    ) = LoginUseCases(
        login = Login(repository)
    )
}








































