package com.kenstarry.snacky.feature_settings.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.presentation.utils.Constants
import com.kenstarry.snacky.feature_settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : SettingsRepository {

    override suspend fun logout(
        onResponse: (response: Response<*>) -> Unit
    ) {
        try {

            auth.signOut()
            onResponse(Response.Success(true))
        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun deleteAccount(
        email: String,
        onResponse: (response: Response<*>) -> Unit
    ) {

        try {

            auth.currentUser?.let { user ->

                user.delete()
                    .addOnSuccessListener {
                        //  delete document from firestore
                        db.collection(Constants.USERS_COLLECTION)
                            .document(email)
                            .delete()
                            .addOnSuccessListener { onResponse(Response.Success(true)) }
                            .addOnFailureListener { onResponse(Response.Failure(it.localizedMessage)) }
                    }
                    .addOnFailureListener { onResponse(Response.Failure(it.localizedMessage)) }
            }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }
}
























