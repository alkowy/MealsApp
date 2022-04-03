package com.example.mealsapp.presentation.mealsFromCategoryList

import com.example.mealsapp.domain.model.MealModel

data class MealsFromCategoryState(
    val isLoading: Boolean = false,
    val mealsFromCategory: List<MealModel> = emptyList(),
    val error: String = ""
)
