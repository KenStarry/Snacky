package com.kenstarry.snacky.feature_home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.feature_home.domain.model.HomeEvents
import com.kenstarry.snacky.feature_home.domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel(){

    private val _categories = mutableStateOf<List<Category>>(emptyList())
    val categories: State<List<Category>> = _categories

    fun onEvent(event: HomeEvents) {

        when (event) {
            is HomeEvents.GetCategories -> {
                viewModelScope.launch {
                    useCases.getCategories(
                        categories = {
                            _categories.value = it
                        },
                        response = event.response
                    )
                }
            }
        }
    }
}