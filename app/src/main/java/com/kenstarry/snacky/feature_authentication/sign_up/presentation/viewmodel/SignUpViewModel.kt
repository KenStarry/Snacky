package com.kenstarry.snacky.feature_authentication.sign_up.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.RegistrationFormEvents
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.RegistrationFormState
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.SignUpEvents
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation.ValidationEvent
import com.kenstarry.snacky.feature_authentication.sign_up.domain.model.validation.ValidationResult
import com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.SignUpUseCases
import com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case.validation.ValidateUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: SignUpUseCases,
    private val validationUseCases: ValidateUseCases
) : ViewModel() {

    var formState by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents: Flow<ValidationEvent> = validationEventChannel.receiveAsFlow()

    fun onFormEvent(event: RegistrationFormEvents) {

        when (event) {

            is RegistrationFormEvents.OnUsernameChanged -> {
                formState = formState.copy(username = event.username)
            }

            is RegistrationFormEvents.OnEmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is RegistrationFormEvents.OnPasswordChanged -> {
                formState = formState.copy(password = event.password)
            }

            is RegistrationFormEvents.OnConfirmPasswordChanged -> {
                formState = formState.copy(confirmPassword = event.confirmPass)
            }

            is RegistrationFormEvents.Submit -> {
                submitData()
            }
        }
    }

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

    private fun submitData() {

        //  validate input
        val emailResult: ValidationResult =
            validationUseCases.validateEmail.execute(formState.email)

        val usernameResult: ValidationResult =
            validationUseCases.validateUserName.execute(formState.username)

        val passResult: ValidationResult =
            validationUseCases.validatePassword.execute(formState.password)

        val confirmPassResult: ValidationResult =
            validationUseCases.validateConfirmPassword.execute(
                formState.password, formState.confirmPassword
            )

        val hasError = listOf(
            emailResult,
            usernameResult,
            passResult,
            confirmPassResult
        ).any { !it.successful }

        if (hasError) {

            formState = formState.copy(
                emailError = emailResult.errorMessage,
                usernameError = usernameResult.errorMessage,
                passwordError = passResult.errorMessage,
                confirmPasswordError = confirmPassResult.errorMessage
            )

            //  send an error message to the validation channel
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        } else {
            viewModelScope.launch {
                validationEventChannel.send(ValidationEvent.Success)
            }
        }
    }
}




































