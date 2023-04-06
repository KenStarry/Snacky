package com.kenstarry.snacky.feature_authentication.login.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.feature_authentication.login.domain.model.LoginEvents
import com.kenstarry.snacky.feature_authentication.login.domain.model.form.LoginFormEvents
import com.kenstarry.snacky.feature_authentication.login.domain.model.form.LoginFormState
import com.kenstarry.snacky.feature_authentication.login.domain.use_case.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCases
) : ViewModel() {

    var formState by mutableStateOf(LoginFormState())

    fun onFormEvent(event: LoginFormEvents) {

        when (event) {

            is LoginFormEvents.OnLoginEmailChanged -> {
                formState = formState.copy(email = event.email)
            }
            is LoginFormEvents.OnLoginPasswordChanged -> {
                formState = formState.copy(password = event.password)
            }
        }
    }

    fun onEvent(event: LoginEvents) {

        when (event) {

            is LoginEvents.LoginUser -> {
                viewModelScope.launch {
                    useCases.login(
                        email = event.email,
                        password = event.password,
                        onResponse = event.onResponse
                    )
                }
            }
        }
    }
}























