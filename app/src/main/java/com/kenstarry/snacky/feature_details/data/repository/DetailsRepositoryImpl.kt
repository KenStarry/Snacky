package com.kenstarry.snacky.feature_details.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.core.domain.model.Cart
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.presentation.utils.Constants
import com.kenstarry.snacky.feature_details.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : DetailsRepository {

    override suspend fun getSnack(
        categoryTitle: String,
        snackTitle: String,
        snack: (snack: Snack) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {

        try {
            db.collection(Constants.CATEGORIES_COLLECTION)
                .document(categoryTitle)
                .collection(Constants.SNACKS_SUB_COLLECTION)
                .document(snackTitle)
                .addSnapshotListener { snapshot, error ->

                    if (error != null) {
                        response(Response.Failure(error))
                        return@addSnapshotListener
                    }

                    snapshot?.toObject(Snack::class.java)?.let(snack)
                }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun updateSnackFavorites(
        email: String,
        snackTitle: String,
        isAdd: Boolean,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            val collectionRef = db.collection(Constants.USERS_COLLECTION)
                .document(email)

            if (isAdd) {
                collectionRef.update("userSnackFavourites", FieldValue.arrayUnion(snackTitle))
                    .addOnSuccessListener { response(Response.Success(true)) }
                    .addOnFailureListener { response(Response.Failure(it.localizedMessage)) }
            } else {
                collectionRef.update("userSnackFavourites", FieldValue.arrayRemove(snackTitle))
                    .addOnSuccessListener { response(Response.Success(true)) }
                    .addOnFailureListener { response(Response.Failure(it.localizedMessage)) }
            }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun updateCartItems(
        email: String,
        cart: Cart,
        isAdd: Boolean,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            val collectionRef = db.collection(Constants.USERS_COLLECTION)
                .document(email)

            if (isAdd) {
                collectionRef.update("userCartItems", FieldValue.arrayUnion(cart))
                    .addOnSuccessListener { response(Response.Success(true)) }
                    .addOnFailureListener { response(Response.Failure(it.localizedMessage)) }
            } else {
                collectionRef.update("userCartItems", FieldValue.arrayRemove(cart))
                    .addOnSuccessListener { response(Response.Success(true)) }
                    .addOnFailureListener { response(Response.Failure(it.localizedMessage)) }
            }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }
}




























