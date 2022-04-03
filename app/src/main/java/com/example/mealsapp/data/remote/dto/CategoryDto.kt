package com.example.mealsapp.data.remote.dto


import com.example.mealsapp.domain.model.CategoryModel
import com.google.gson.annotations.SerializedName

data class CategoryDto(
        @SerializedName("idCategory")
        val idCategory: String,
        @SerializedName("strCategory")
        val strCategoryName: String,
        @SerializedName("strCategoryDescription")
        val strCategoryDescription: String,
        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String
)

fun CategoryDto.toCategory() : CategoryModel{
        return CategoryModel(
                id = idCategory,
                name = strCategoryName,
                image = strCategoryThumb
        )
}