package com.example.mealsapp.domain.use_case.get_meals_by_category

import com.example.mealsapp.common.Resource
import com.example.mealsapp.data.remote.dto.toMeal
import com.example.mealsapp.domain.model.MealModel
import com.example.mealsapp.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(
        private val repository: MealRepository
) {
    operator fun invoke(categoryStr: String): Flow<Resource<List<MealModel>>> = flow {
        try {
            emit(Resource.Loading())
            val meals = repository.getMealsByCategory(categoryStr).meals.map { it.toMeal() }
            emit(Resource.Success(meals))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection."))
        }
    }
}