package com.kenstarry.snacky.feature_authentication.sign_up.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.SignUpEvents
import com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.CreateAccount
import com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: SignUpUseCases
) : ViewModel() {

    fun onEvent(event: SignUpEvents) {

        when (event) {

            is SignUpEvents.CreateAccount -> {
                viewModelScope.launch {
                    useCases.createAccount(
                        user = event.user,
                        response = event.response
                    )
                }
            }

            is SignUpEvents.UploadImage -> {
                viewModelScope.launch {
                    useCases.uploadImageToStorage(
                        uri = event.uri,
                        context = event.context,
                        storageRef = event.storageRef,
                        collectionName = event.collectionName,
                        documentName = event.documentName,
                        subCollectionName = event.subCollectionName,
                        subCollectionDocument = event.subCollectionDocument,
                        fieldToUpdate = event.fieldToUpdate,
                        onResponse = event.onResponse
                    )
                }
            }
        }
    }
}