package com.example.mealsapp.presentation.mealDetail.viewPagerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mealsapp.R
import com.example.mealsapp.databinding.FragmentMealDetailBinding
import com.example.mealsapp.databinding.FragmentMealIngredientsBinding
import com.example.mealsapp.domain.model.MealDetailModel

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
        binding.ingredientsPageTV.text = "some ingredients here todo"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}