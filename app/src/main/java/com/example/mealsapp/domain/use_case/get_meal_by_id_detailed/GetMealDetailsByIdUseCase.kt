package com.example.mealsapp.domain.use_case.get_meal_by_id_detailed

import com.example.mealsapp.common.Resource
import com.example.mealsapp.data.remote.dto.toMealDetail
import com.example.mealsapp.domain.model.MealDetailModel
import com.example.mealsapp.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsByIdUseCase @Inject constructor(
        private val repository: MealRepository
) {
    operator fun invoke(mealId: String): Flow<Resource<MealDetailModel>> = flow {
        try {
            emit(Resource.Loading())
            val mealDetail = repository.getMealDetailsById(mealId).toMealDetail()
            emit(Resource.Success(mealDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection."))
        }
    }
}