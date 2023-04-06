package com.kenstarry.snacky.feature_home.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.feature_home.data.repository.HomeRepositoryImpl
import com.kenstarry.snacky.feature_home.domain.repository.HomeRepository
import com.kenstarry.snacky.feature_home.domain.use_case.GetCategories
import com.kenstarry.snacky.feature_home.domain.use_case.GetSnacks
import com.kenstarry.snacky.feature_home.domain.use_case.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        db: FirebaseFirestore
    ): HomeRepository = HomeRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideHomeUseCases(
        repository: HomeRepository
    ) = HomeUseCases(
        getCategories = GetCategories(repository),
        getSnacks = GetSnacks(repository)
    )
}














