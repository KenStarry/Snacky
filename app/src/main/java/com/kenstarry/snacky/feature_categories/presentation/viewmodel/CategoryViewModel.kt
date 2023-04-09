package com.kenstarry.snacky.feature_categories.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_categories.domain.model.CategoryEvents
import com.kenstarry.snacky.feature_categories.domain.use_case.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCases: CategoryUseCases
) : ViewModel(){

    private val _snacksInCategory = mutableStateOf<List<Snack>>(emptyList())
    val snacksInCategory: State<List<Snack>> = _snacksInCategory

    private val _categoryDetails = mutableStateOf<Category?>(null)
    val categoryDetails: State<Category?> = _categoryDetails

    fun onEvent(event: CategoryEvents) {

        when (event) {

            is CategoryEvents.GetSnacksInCategory -> {
                viewModelScope.launch {
                    useCases.getSnacksInCategory(
                        category = event.category,
                        snacks = {
                            _snacksInCategory.value = it
                        },
                        onResponse = event.onResponse
                    )
                }
            }

            is CategoryEvents.GetCategoryDetails -> {
                viewModelScope.launch {
                    useCases.getCategoryDetails(
                        category = event.category,
                        categoryDetails = {
                            _categoryDetails.value = it
                        },
                        onResponse = event.onResponse
                    )
                }
            }
        }
    }
}