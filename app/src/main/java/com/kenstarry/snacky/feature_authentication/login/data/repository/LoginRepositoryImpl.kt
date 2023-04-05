package com.kenstarry.snacky.feature_authentication.login.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_authentication.login.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val auth: FirebaseAuth
) : LoginRepository{

    override suspend fun loginUser(
        email: String,
        password: String,
        onResponse: (res: Response<*>) -> Unit
    ) {
        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onResponse(Response.Success(it))
                }
                .addOnFailureListener {
                    onResponse(Response.Failure(it.localizedMessage))
                    Log.d("LOGIN", "Login Failed")
                }

        } catch (e: Exception) {
            onResponse(Response.Failure(e))
        }
    }
}