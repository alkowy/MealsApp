package com.example.mealsapp.data.remote.dto


import com.example.mealsapp.domain.model.MealModel
import com.google.gson.annotations.SerializedName

data class MealDto(
        @SerializedName("idMeal")
        val idMeal: String,
        @SerializedName("strMeal")
        val strMeal: String,
        @SerializedName("strMealThumb")
        val strMealThumb: String
)

fun MealDto.toMeal(): MealModel {
    return MealModel(
            id = idMeal,
            name = strMeal,
            image = strMealThumb
    )
}