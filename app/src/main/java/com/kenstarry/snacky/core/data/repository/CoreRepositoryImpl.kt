package com.kenstarry.snacky.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.core.domain.repository.CoreRepository
import com.kenstarry.snacky.feature_authentication.AuthConstants

class CoreRepositoryImpl(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CoreRepository {

    override suspend fun isUserLoggedIn() = auth.currentUser != null

    override suspend fun currentUser(): FirebaseUser? = auth.currentUser

    override suspend fun getUserDetails(
        email: String,
        user: (user: User?) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) {
        try {
            db.collection(AuthConstants.USERS_COLLECTION)
                .document(email)
                .addSnapshotListener { snapshot, error ->

                    if (error != null) {
                        onResponse(Response.Failure(error))
                        return@addSnapshotListener
                    }

                    snapshot?.toObject(User::class.java)?.let {
                        user(it)
                        onResponse(Response.Success(it))
                    }
                }
        } catch (e: Exception) {
            onResponse(Response.Failure(e))
        }
    }
}