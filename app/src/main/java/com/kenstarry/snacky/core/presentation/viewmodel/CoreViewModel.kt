package com.kenstarry.snacky.core.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.domain.use_case.CoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val useCases: CoreUseCases
): ViewModel() {

    private val _userDetails = mutableStateOf<User?>(null)
    val userDetails: State<User?> = _userDetails

    private val _currentUser = mutableStateOf<FirebaseUser?>(null)
    private val _isLoggedIn = mutableStateOf<Boolean>(false)

    private val _colorPalette = mutableStateOf<Map<String, String>>(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun getCurrentUser(): FirebaseUser? {
        viewModelScope.launch {
            _currentUser.value = useCases.currentUser.invoke()
        }

        return _currentUser.value
    }

    fun isUserLoggedIn(): Boolean {
        viewModelScope.launch {
            _isLoggedIn.value = useCases.isLoggedIn.invoke()
        }

        return _isLoggedIn.value
    }

    fun onEvent(event: CoreEvents) {

        when (event) {

            is CoreEvents.SetColorPalette -> {
                _colorPalette.value = event.colors
            }

            is CoreEvents.GetUserDetails -> {
                viewModelScope.launch {
                    useCases.getUserDetails(
                        email = event.email,
                        user = {
                            _userDetails.value = it
                        },
                        onResponse = event.onResponse
                    )
                }
            }
        }
    }
}

































