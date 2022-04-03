package com.example.mealsapp.presentation.categoriesList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.common.Resource
import com.example.mealsapp.domain.use_case.get_categories.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesState())
    val state: StateFlow<CategoriesState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CategoriesState(
                        categories = result.data ?: emptyList()
                    )
                    Log.d("categoriesViewModel",result.data.toString())
                }
                is Resource.Error -> {
                    _state.value = CategoriesState(
                        error = result.message ?: "Unexpected error occurred."
                    )
                    Log.d("categoriesViewModel",result.message.toString())

                }
                is Resource.Loading -> {
                    _state.value = CategoriesState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


}