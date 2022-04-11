package com.example.mealsapp.presentation.mealsFromCategoryList

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.common.Resource
import com.example.mealsapp.domain.use_case.get_meal_by_id_detailed.GetMealDetailsByIdUseCase
import com.example.mealsapp.domain.use_case.get_meals_by_category.GetMealsByCategoryUseCase
import com.example.mealsapp.presentation.categoriesList.CategoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealsFromCategoryViewModel @Inject constructor(
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MealsFromCategoryState())
    val state: StateFlow<MealsFromCategoryState> = _state

    init {
        savedStateHandle.get<String>("category_name")?.let { categoryName ->
            getMealsByCategory(categoryName)
        }
    }
    fun saveCurrentCategory (categoryName : String){
        savedStateHandle.set("category_name",categoryName)
    }
    fun getCurrentCategory(){
        savedStateHandle.get<String>("category_name")?.let { categoryName ->
            getMealsByCategory(categoryName)
        }
    }

    private fun getMealsByCategory(category: String) {
        getMealsByCategoryUseCase(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MealsFromCategoryState(
                        mealsFromCategory = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = MealsFromCategoryState(
                        error = result.message ?: "Unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = MealsFromCategoryState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


}