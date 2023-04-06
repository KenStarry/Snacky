package com.kenstarry.snacky.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.core.data.repository.CoreRepositoryImpl
import com.kenstarry.snacky.core.domain.repository.CoreRepository
import com.kenstarry.snacky.core.domain.use_case.CoreUseCases
import com.kenstarry.snacky.core.domain.use_case.CurrentUser
import com.kenstarry.snacky.core.domain.use_case.GetUserDetails
import com.kenstarry.snacky.core.domain.use_case.IsLoggedIn
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideCoreRepository(
        db: FirebaseFirestore,
        auth: FirebaseAuth
    ): CoreRepository = CoreRepositoryImpl(db, auth)

    @Provides
    @Singleton
    fun provideCoreUseCases(
        repository: CoreRepository
    ) = CoreUseCases(
        currentUser = CurrentUser(repository),
        isLoggedIn = IsLoggedIn(repository),
        getUserDetails = GetUserDetails(repository)
    )
}































