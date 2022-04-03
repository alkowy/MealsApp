package com.example.mealsapp.presentation.categoriesList

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mealsapp.R
import com.example.mealsapp.databinding.FragmentCategoriesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.internal.notify


private var _binding: FragmentCategoriesListBinding? = null
private val binding get() = _binding!!
private lateinit var navController: NavController
private lateinit var categoriesAdapter: CategoriesAdapter
private lateinit var categoriesState: CategoriesState

@AndroidEntryPoint
class CategoriesListFragment : Fragment() {
    private val viewModel = hiltNavGraphViewModels<CategoriesViewModel>(R.id.nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesListBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvCategories = binding.recyclerViewCategories
        categoriesAdapter = CategoriesAdapter(emptyList())
        rvCategories.adapter = categoriesAdapter
        rvCategories.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.value.state.collect { state ->
                    if (state.isLoading) {
                        binding.categoriesProgressBar.visibility = View.VISIBLE
                        binding.categoriesGroup.visibility = View.INVISIBLE
                    }
                    if (state.error.isNotBlank()) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                    } else if (state.categories.isNotEmpty()){
                        categoriesAdapter.setCategories(state.categories)
                        rvCategories.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
                        delay(300)
                        binding.categoriesProgressBar.visibility = View.GONE
                        binding.categoriesGroup.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}