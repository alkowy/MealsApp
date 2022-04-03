package com.example.mealsapp.presentation.mealDetail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mealsapp.R
import com.example.mealsapp.common.Constants
import com.example.mealsapp.databinding.FragmentMealDetailBinding
import com.example.mealsapp.domain.model.MealDetailModel
import com.example.mealsapp.presentation.mealDetail.viewPagerFragments.MealIngredientsFragment
import com.example.mealsapp.presentation.mealDetail.viewPagerFragments.MealInstructionsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

private var _binding: FragmentMealDetailBinding? = null
private val binding get() = _binding!!
private lateinit var mealDetailsId: String
private lateinit var pagerAdapter: ViewPagerAdapter

class MealDetailFragment : Fragment() {
    private val viewModel = hiltNavGraphViewModels<MealDetailViewModel>(R.id.nav_graph)
    private lateinit var navController: NavController
    private var fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealDetailsId = it.get("meal_id") as String
        }
        _binding = FragmentMealDetailBinding.inflate(layoutInflater)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.value.saveCurrentDetailedMealId(mealDetailsId)
        viewModel.value.getCurrentDetailedMealId()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.value.state.collect { state ->
                    if (state.isLoading) {

                    }
                    if (state.error.isNotBlank()) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                    }
                    if (state.mealDetail?.strMeal != null) {
                        val meal = state.mealDetail
                        fragmentList.add(MealIngredientsFragment(meal))
                        fragmentList.add(MealInstructionsFragment(meal))
                        pagerAdapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
                        binding.mealDetailViewPager.adapter = pagerAdapter
                        val tabLayout = binding.mealDetailsTabLayout
                        val tabLayoutMediator = TabLayoutMediator(
                            tabLayout,
                            binding.mealDetailViewPager,
                            object : TabLayoutMediator.TabConfigurationStrategy{
                                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                                    tab.text = Constants.tabNames[position]
                                }
                            }
                        ).attach()

                        Glide.with(binding.root)
                            .load(meal.strMealThumb)
                            .into(binding.mealDetailImage)

                        binding.mealDetailName.text = meal.strMeal
                        binding.mealDetailInstructions.text = meal.strInstructions

                    }
                }
            }
        }


    }


}
