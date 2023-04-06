package com.kenstarry.snacky.core.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User

interface CoreRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun currentUser(): FirebaseUser?

    suspend fun getUserDetails(
        email: String,
        user: (user: User?) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    )
}