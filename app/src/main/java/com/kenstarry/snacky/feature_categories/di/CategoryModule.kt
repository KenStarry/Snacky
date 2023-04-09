package com.kenstarry.snacky.feature_categories.di

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.feature_categories.data.respository.CategoryRepositoryImpl
import com.kenstarry.snacky.feature_categories.domain.repository.CategoryRepository
import com.kenstarry.snacky.feature_categories.domain.use_case.CategoryUseCases
import com.kenstarry.snacky.feature_categories.domain.use_case.GetCategoryDetails
import com.kenstarry.snacky.feature_categories.domain.use_case.GetSnacksInCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        db: FirebaseFirestore
    ): CategoryRepository = CategoryRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideCategoryUseCases(
        repository: CategoryRepository
    ) = CategoryUseCases(
        getSnacksInCategory = GetSnacksInCategory(repository),
        getCategoryDetails = GetCategoryDetails(repository)
    )
}

















