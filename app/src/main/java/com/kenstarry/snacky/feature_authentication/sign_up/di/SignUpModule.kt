package com.kenstarry.snacky.feature_authentication.sign_up.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kenstarry.snacky.feature_authentication.sign_up.data.repository.SignUpRepositoryImpl
import com.kenstarry.snacky.feature_authentication.sign_up.domain.repository.SignUpRepository
import com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.CreateAccount
import com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.SignUpUseCases
import com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.UploadImageToStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {

    @Provides
    @Singleton
    fun provideSignUpRepository(
        db: FirebaseFirestore,
        auth: FirebaseAuth,
        storage: FirebaseStorage
    ) : SignUpRepository = SignUpRepositoryImpl(db, auth, storage)

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        repository: SignUpRepository
    ) = SignUpUseCases(
        createAccount = CreateAccount(repository),
        uploadImageToStorage = UploadImageToStorage(repository)
    )
}


















