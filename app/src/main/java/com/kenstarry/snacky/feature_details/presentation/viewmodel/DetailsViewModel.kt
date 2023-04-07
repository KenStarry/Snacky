package com.kenstarry.snacky.feature_details.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.domain.use_case.DetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: DetailsUseCases
) : ViewModel() {

    private val _snack = mutableStateOf<Snack?>(null)
    val snack: State<Snack?> = _snack

    private val _isFavoriteToggled = mutableStateOf<Boolean>(false)
    val isFavoriteToggled: State<Boolean> = _isFavoriteToggled

    fun onEvent(event: DetailsEvents) {

        when (event) {
            is DetailsEvents.GetSnack -> {
                viewModelScope.launch {
                    useCases.getSnack(
                        categoryTitle = event.categoryTitle,
                        snackTitle = event.snackTitle,
                        snack = { _snack.value = it },
                        response = event.response
                    )
                }
            }

            is DetailsEvents.UpdateSnackFavorites -> {
                viewModelScope.launch {
                    useCases.updateSnackFavorites(
                        email = event.email,
                        snackTitle = event.snackTitle,
                        isAdd = event.isAdd,
                        response = event.response
                    )
                }
            }

            is DetailsEvents.ToggleFavoritesButton -> {
                _isFavoriteToggled.value = event.isFavoriteClicked
            }
        }
    }
}



















