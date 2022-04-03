package com.example.mealsapp.domain.use_case.get_categories

import com.example.mealsapp.common.Resource
import com.example.mealsapp.data.remote.dto.toCategory
import com.example.mealsapp.domain.model.CategoryModel
import com.example.mealsapp.domain.repository.MealRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
        private val repository: MealRepository
) {
    operator fun invoke(): Flow<Resource<List<CategoryModel>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = repository.getAllCategories().categories.map { it.toCategory() }
            emit(Resource.Success(categories))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection."))
        }
    }
}