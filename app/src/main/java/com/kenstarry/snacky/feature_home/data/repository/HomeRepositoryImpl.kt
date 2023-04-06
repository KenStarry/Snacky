package com.kenstarry.snacky.feature_home.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.presentation.utils.Constants
import com.kenstarry.snacky.feature_home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : HomeRepository {

    override suspend fun getSnackCategories(
        categories: (categories: List<Category>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {

        try {

            //  get all categories
            db.collection(Constants.CATEGORIES_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        response(Response.Failure(error))
                        return@addSnapshotListener
                    }

                    //  create a list to hold for us the categories
                    val categoriesList = mutableListOf<Category>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(Category::class.java)?.let { category ->
                            categoriesList.add(category)
                        }
                    }

                    categories(categoriesList)
                    response(Response.Success(categoriesList))
                }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun getSnacks(
        snacks: (snacks: List<Snack>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            db.collectionGroup(Constants.SNACKS_SUB_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        response(Response.Failure(error))
                        return@addSnapshotListener
                    }

                    val snacksList = mutableListOf<Snack>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(Snack::class.java)?.let {
                            snacksList.add(it)
                        }
                    }
                    response(Response.Success(snacksList))
                    snacks(snacksList)
                }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }
}

































