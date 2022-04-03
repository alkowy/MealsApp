package com.example.mealsapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MealsFromCategoryDto(
        @SerializedName("meals")
        val meals: List<MealDto>
)