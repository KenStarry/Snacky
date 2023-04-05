package com.kenstarry.snacky.feature_authentication.sign_up.domain.repository

import android.content.Context
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User

interface SignUpRepository {

    suspend fun createAccount(
        user: User,
        response: (onResponse: Response<*>) -> Unit
    )

    suspend fun uploadImageToStorage(
        uri: String?,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    )
}