package com.example.mealsapp.data.remote

import com.example.mealsapp.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealApi {

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryStr: String): MealsFromCategoryDto

    @GET("categories.php")
    suspend fun getAllCategories(): CategoriesDto

    @GET("lookup.php")
    suspend fun getMealDetailsById(@Query("i") mealId: String) : MealDetailDto
}