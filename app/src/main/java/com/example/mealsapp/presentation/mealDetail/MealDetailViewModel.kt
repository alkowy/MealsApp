package com.example.mealsapp.presentation.mealDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.common.Resource
import com.example.mealsapp.domain.use_case.get_meal_by_id_detailed.GetMealDetailsByIdUseCase
import com.example.mealsapp.presentation.categoriesList.CategoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val getMealDetailsByIdUseCase: GetMealDetailsByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = MutableStateFlow(MealDetailState())
    val state: StateFlow<MealDetailState> = _state

    init {
        savedStateHandle.get<String>("mealId")?.let { mealId ->
            getMealDetailsById(mealId)
        }
    }
    fun saveCurrentDetailedMealId (detailedMealId : String){
        savedStateHandle.set("meal_id",detailedMealId)
    }
    fun getCurrentDetailedMealId(){
        savedStateHandle.get<String>("meal_id")?.let { mealId ->
            getMealDetailsById(mealId)
        }
    }

    private fun getMealDetailsById(mealId: String) {
        getMealDetailsByIdUseCase(mealId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MealDetailState(
                        mealDetail = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = MealDetailState(
                        error = result.message ?: "Unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = MealDetailState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


}