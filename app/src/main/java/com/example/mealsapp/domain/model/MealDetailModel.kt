package com.example.mealsapp.domain.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.StringBuilder
import java.util.*


data class MealDetailModel(
    val mealId: String,
    val strMeal: String? = null,
    val strMealThumb: String? = null,
    val strInstructions: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,
    val strIngredient16: String? = null,
    val strIngredient17: String? = null,
    val strIngredient18: String? = null,
    val strIngredient19: String? = null,
    val strIngredient20: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure9: String? = null,
    val strMeasure8: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null,
    val strMeasure16: String? = null,
    val strMeasure17: String? = null,
    val strMeasure18: String? = null,
    val strMeasure19: String? = null,
    val strMeasure20: String? = null,
) {
    fun ingredientsToString(): String {
        val fullCircleSymbol = String(Character.toChars(0x2B24))
        var ingredientsAndMeasuresList = listOf(
            strIngredient1, strMeasure1,
            strIngredient2, strMeasure2,
            strIngredient3, strMeasure3,
            strIngredient4, strMeasure4,
            strIngredient5, strMeasure5,
            strIngredient6, strMeasure6,
            strIngredient7, strMeasure7,
            strIngredient8, strMeasure8,
            strIngredient9, strMeasure9,
            strIngredient10, strMeasure10,
            strIngredient11, strMeasure11,
            strIngredient12, strMeasure12,
            strIngredient13, strMeasure13,
            strIngredient14, strMeasure14,
            strIngredient15, strMeasure15,
            strIngredient16, strMeasure16,
            strIngredient17, strMeasure17,
            strIngredient18, strMeasure18,
            strIngredient19, strMeasure19,
            strIngredient20, strMeasure20,
        )
        ingredientsAndMeasuresList = ingredientsAndMeasuresList.filterNotNull().filter { it.isNotBlank() }
        val stringBuilder = StringBuilder()
        for (i in ingredientsAndMeasuresList.indices step 2) {
            stringBuilder
                .append(fullCircleSymbol)
                .append(" ${ingredientsAndMeasuresList[i]} - ${ingredientsAndMeasuresList[i.inc()]}\n")
        }
        return stringBuilder.toString()
    }


}

