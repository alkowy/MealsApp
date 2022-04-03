package com.example.mealsapp.presentation.mealDetail.viewPagerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mealsapp.R
import com.example.mealsapp.databinding.FragmentMealIngredientsBinding
import com.example.mealsapp.databinding.FragmentMealInstructionsBinding
import com.example.mealsapp.domain.model.MealDetailModel

private var _binding: FragmentMealInstructionsBinding? = null
private val binding get() = _binding!!
class MealInstructionsFragment (mealDetail : MealDetailModel) : Fragment() {

    private val meal = mealDetail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealInstructionsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.instructionsPageTV.text = meal.strInstructions
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}