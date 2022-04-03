package com.example.mealsapp.presentation.categoriesList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsapp.R
import com.example.mealsapp.databinding.CategoryItemRvBinding
import com.example.mealsapp.domain.model.CategoryModel

class CategoriesAdapter (private var categories: List<CategoryModel>): RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val itemBinding = CategoryItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val category : CategoryModel = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setCategories (categories: List<CategoryModel>){
        this.categories = categories
        notifyDataSetChanged()
    }

    class CategoriesHolder(private val itemBinding: CategoryItemRvBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: CategoryModel) {
            itemBinding.categoryName.text = category.name
            Log.d("CategoriesAadapter",category.toString())
            Glide.with(itemBinding.root).load(category.image).centerInside().into(itemBinding.categoryImgView)
            itemBinding.root.setOnClickListener {
                Log.d("CategoriesAdapter", "$category clicked")
                Navigation.findNavController(itemBinding.root).navigate(R.id.action_categoriesListFragment_to_mealsFromCategoryFragment, bundleOf("category_name" to category.name))
            }
        }


    }

}