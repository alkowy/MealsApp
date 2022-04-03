package com.example.mealsapp.data.repository

import com.example.mealsapp.data.remote.MealApi
import com.example.mealsapp.data.remote.dto.*
import com.example.mealsapp.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
        private val api: MealApi
) : MealRepository {

    override suspend fun getMealsByCategory(categoryStr: String): MealsFromCategoryDto {
        return api.getMealsByCategory(categoryStr)
    }

    override suspend fun getMealDetailsById(mealId: String): MealDetailDto {
        return api.getMealDetailsById(mealId)
    }

    override suspend fun getAllCategories(): CategoriesDto {
        return api.getAllCategories()
    }
}