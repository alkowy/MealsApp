package com.example.mealsapp.presentation.mealDetail.viewPagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.mealsapp.R
import com.example.mealsapp.databinding.FragmentMealIngredientsBinding
import com.example.mealsapp.domain.model.MealDetailModel
import com.example.mealsapp.presentation.mealDetail.MealDetailViewModel

private var _binding: FragmentMealIngredientsBinding? = null
private val binding get() = _binding!!

class MealIngredientsFragment(mealDetails: MealDetailModel) : Fragment() {

    private val meal = mealDetails

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealIngredientsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MealIngredientsFragment", meal.toString())
        binding.ingredientsPageTV.text = meal.ingredientsToString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}