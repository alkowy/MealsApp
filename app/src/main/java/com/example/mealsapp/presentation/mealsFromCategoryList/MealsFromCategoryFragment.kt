package com.example.mealsapp.presentation.mealsFromCategoryList

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsapp.R
import com.example.mealsapp.databinding.FragmentMealsFromCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var _binding: FragmentMealsFromCategoryBinding? = null
private val binding get() = _binding!!
private lateinit var navController: NavController
private lateinit var mealsAdapter: MealsAdapter
private lateinit var mealsState: MealsFromCategoryState

@AndroidEntryPoint
class MealsFromCategoryFragment : Fragment() {
    private val viewModel = hiltNavGraphViewModels<MealsFromCategoryViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMealsFromCategoryBinding.inflate(layoutInflater)
        navController = findNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMeals = binding.recyclerViewMealsInCategory
        mealsAdapter = MealsAdapter(ArrayList())
        mealsAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        rvMeals.adapter = mealsAdapter
        rvMeals.layoutManager = LinearLayoutManager(context)
        val categoryName = arguments?.get("category_name") ?: ""
        viewModel.value.saveCurrentCategory(categoryName as String)
        viewModel.value.getCurrentCategory()

        binding.toolbar.title = categoryName
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mealsAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mealsAdapter.filter.filter(newText)
                return true
            }
        })
        binding.toolbar.setNavigationOnClickListener {
            navController.navigate(R.id.action_mealsFromCategoryFragment_to_categoriesListFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.value.state.collect { state ->
                    if (state.isLoading) {
                        binding.recyclerViewMealsInCategory.visibility = View.INVISIBLE
                    }
                    if (state.error.isNotBlank()) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                    } else if (state.mealsFromCategory.isNotEmpty()) {
                        mealsAdapter.setMeals(state.mealsFromCategory)
                        delay(300)
                        binding.mealsFromCategoryProgressBar.visibility = View.INVISIBLE
                        binding.mealsFromCategoryGroup.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        //to clear and collapse the searchview
        binding.searchView.setQuery("",false)
        binding.searchView.clearFocus()
        binding.searchView.isIconified = true
    }
}