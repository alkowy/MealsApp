package com.example.mealsapp.presentation.mealDetail

import com.example.mealsapp.domain.model.MealDetailModel

data class MealDetailState(
    val isLoading: Boolean = false,
    val mealDetail: MealDetailModel? = null,
    val error: String = ""
)
