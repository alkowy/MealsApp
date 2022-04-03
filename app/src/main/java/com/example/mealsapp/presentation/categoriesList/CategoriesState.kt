package com.example.mealsapp.presentation.categoriesList

import com.example.mealsapp.domain.model.CategoryModel

data class CategoriesState(
    val isLoading: Boolean = false,
    val categories: List<CategoryModel> = emptyList(),
    val error: String = ""
)
