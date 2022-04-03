package com.example.mealsapp.data.remote.dto


import com.example.mealsapp.domain.model.CategoryModel
import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("categories")
    val categories: List<CategoryDto>
)

