package com.kenstarry.snacky.feature_home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.feature_home.domain.model.HomeEvents
import com.kenstarry.snacky.feature_home.domain.use_case.GetSnacks
import com.kenstarry.snacky.feature_home.domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {

    private val _categories = mutableStateOf<List<Category>>(emptyList())
    val categories: State<List<Category>> = _categories

    private val _snacks = mutableStateOf<List<Snack>>(emptyList())
    val snacks: State<List<Snack>> = _snacks

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

            is HomeEvents.GetSnacks -> {
                viewModelScope.launch {
                    useCases.getSnacks(
                        snacks = {
                            _snacks.value = it
                        },
                        response = {
                            event.response

                            when (it) {
                                is Response.Success -> {
                                    _snacks.value = it.data as List<Snack>
                                }

                                is Response.Failure -> {

                                }
                            }
                        }
                    )
                }
            }

            is HomeEvents.SearchForSnacks -> {
                viewModelScope.launch {

                    val allSnacks = _snacks.value.toMutableList()
                    val filteredSnacks = mutableListOf<Snack>()

                    allSnacks.forEach { snack ->
                        if (snack.snackName.title.lowercase().contains(event.query.lowercase()) ||
                            snack.snackCategory.lowercase().contains(event.query.lowercase())
                        ) {
                            filteredSnacks.add(snack)
                        }
                    }

                    event.filteredSnacks(filteredSnacks)
                }
            }
        }
    }
}