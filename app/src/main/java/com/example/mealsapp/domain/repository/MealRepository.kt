package com.example.mealsapp.domain.repository

import com.example.mealsapp.data.remote.dto.*

interface MealRepository {

    suspend fun getMealsByCategory (categoryStr: String) : MealsFromCategoryDto

    suspend fun getMealDetailsById (mealId: String) : MealDetailDto

    suspend fun getAllCategories() : CategoriesDto
}