package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case

import android.content.Context
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.feature_authentication.sign_up.domain.repository.SignUpRepository

class UploadImageToStorage(
    private val repo: SignUpRepository
) {
    suspend operator fun invoke(
        uri: String?,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    ) = repo.uploadImageToStorage(
        uri,
        context,
        storageRef,
        collectionName,
        documentName,
        subCollectionName,
        subCollectionDocument,
        fieldToUpdate,
        onResponse
    )
}

































