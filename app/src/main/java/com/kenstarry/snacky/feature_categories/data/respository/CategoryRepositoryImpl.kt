package com.kenstarry.snacky.feature_categories.data.respository

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.presentation.utils.Constants
import com.kenstarry.snacky.feature_categories.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : CategoryRepository {

    override suspend fun getSnacksInCategory(
        category: String,
        snacks: (snacksList: List<Snack>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.CATEGORIES_COLLECTION)
                .document(category)
                .collection(Constants.SNACKS_SUB_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        onResponse(Response.Failure(error))
                        return@addSnapshotListener
                    }

                    val snacksList = mutableListOf<Snack>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(Snack::class.java)?.let { snack ->
                            snacksList.add(snack)
                        }
                    }

                    snacks(snacksList)
                    onResponse(Response.Success(snacksList))

                }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun getCategoryDetails(
        category: String,
        categoryDetails: (details: Category) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.CATEGORIES_COLLECTION)
                .document(category)
                .addSnapshotListener { snapshot, error ->

                    if (error != null) {
                        onResponse(Response.Failure(error))
                        return@addSnapshotListener
                    }

                    snapshot?.toObject(Category::class.java)?.let {
                        categoryDetails(it)
                        onResponse(Response.Success(it))
                    }
                }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }
}






























