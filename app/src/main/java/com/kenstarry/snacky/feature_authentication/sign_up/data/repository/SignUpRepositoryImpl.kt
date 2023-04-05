package com.kenstarry.snacky.feature_authentication.sign_up.data.repository

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.feature_authentication.AuthConstants
import com.kenstarry.snacky.feature_authentication.sign_up.domain.repository.SignUpRepository

class SignUpRepositoryImpl(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
) : SignUpRepository {

    override suspend fun createAccount(user: User, response: (onResponse: Response<*>) -> Unit) {

        try {

            auth.createUserWithEmailAndPassword(
                user.userEmail,
                user.userPassword
            ).addOnSuccessListener {

                //  create user in firestore
                try {

                    db.collection(AuthConstants.USERS_COLLECTION)
                        .document(user.userEmail)
                        .set(user)
                        .addOnSuccessListener { response(Response.Success(true)) }
                        .addOnFailureListener { response(Response.Failure(it.localizedMessage)) }

                } catch (e: Exception) {
                    response(Response.Failure(e.localizedMessage))
                }

                response(Response.Success(true))
            }
                .addOnFailureListener { response(Response.Failure(it.localizedMessage)) }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun uploadImageToStorage(
        uri: String?,
        context: Context,
        storageRef: String,
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        fieldToUpdate: String,
        onResponse: (response: Response<*>) -> Unit
    ) {
        //  get storage reference
        val ref = FirebaseStorage
            .getInstance()
            .getReference(storageRef)

        try {

            uri?.toUri()?.let { imageUri ->

                val fileExtension = getFileExtension(imageUri, context)
                val fileRef = ref.child(
                    "${System.currentTimeMillis()}.$fileExtension"
                )

                fileRef.putFile(imageUri)
                    .addOnSuccessListener {

                        try {

                            //  getting the download url
                            fileRef.downloadUrl.addOnSuccessListener { url ->
                                db.collection(collectionName)
                                    .document(documentName)
                                    .update(
                                        fieldToUpdate,
                                        url
                                    )

                                onResponse(Response.Success(url))
                            }

                            onResponse(Response.Success(it))

                        } catch (e: Exception) {
                            onResponse(Response.Failure(e.localizedMessage))
                        }
                    }
                    .addOnFailureListener {}
            }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }

    fun getFileExtension(uri: Uri, context: Context): String? {

        val cr = context.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()

        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
    }
}