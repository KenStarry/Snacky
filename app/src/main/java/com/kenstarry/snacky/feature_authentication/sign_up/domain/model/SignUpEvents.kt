package com.kenstarry.snacky.feature_authentication.sign_up.domain.model

import android.content.Context
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User

sealed class SignUpEvents {

    data class CreateAccount(
        val user: User,
        val response: (onResponse: Response<*>) -> Unit
    ) : SignUpEvents()

    data class UploadImage(
        val uri: String?,
        val context: Context,
        val storageRef: String,
        val collectionName: String,
        val documentName: String,
        val subCollectionName: String?,
        val subCollectionDocument: String?,
        val fieldToUpdate: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : SignUpEvents()
}
