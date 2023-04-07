package com.kenstarry.snacky.feature_details.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.feature_details.data.repository.DetailsRepositoryImpl
import com.kenstarry.snacky.feature_details.domain.repository.DetailsRepository
import com.kenstarry.snacky.feature_details.domain.use_case.DetailsUseCases
import com.kenstarry.snacky.feature_details.domain.use_case.GetSnack
import com.kenstarry.snacky.feature_details.domain.use_case.UpdateCartItems
import com.kenstarry.snacky.feature_details.domain.use_case.UpdateSnackFavorites
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    @Singleton
    fun provideDetailsRepository(
        db: FirebaseFirestore
    ): DetailsRepository = DetailsRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideDetailsUseCases(
        repository: DetailsRepository
    ) = DetailsUseCases(
        getSnack = GetSnack(repository),
        updateSnackFavorites = UpdateSnackFavorites(repository),
        updateCartItems = UpdateCartItems(repository)
    )
}
