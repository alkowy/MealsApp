package com.example.mealsapp.presentation.mealsFromCategoryList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsapp.R
import com.example.mealsapp.databinding.MealItemRvBinding
import com.example.mealsapp.domain.model.MealModel
import java.util.*
import kotlin.collections.ArrayList


class MealsAdapter(private var meals: ArrayList<MealModel>) :
    RecyclerView.Adapter<MealsAdapter.CategoriesHolder>(), Filterable {

     private val initialMealsList = meals
     private val displayList = ArrayList<MealModel>().apply { addAll(initialMealsList) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val itemBinding =
            MealItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val meal: MealModel = displayList[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int {
        return displayList.size
    }

    fun setMeals(meals: List<MealModel>) {
        this.meals = meals as ArrayList<MealModel>
        displayList.clear()
        displayList.addAll(meals)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<MealModel>()
                if (constraint == null || constraint.isEmpty()) {
                    meals.let { filteredList.addAll(it) }
                } else {
                    val query = constraint.toString().trim().lowercase(Locale.getDefault())
                    meals.forEach {
                        if (it.name.lowercase(Locale.getDefault()).contains(query)) {
                            filteredList.add(it)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values is ArrayList<*>) {
                    displayList.clear()
                    displayList.addAll(results.values as ArrayList<MealModel>)
                    notifyDataSetChanged()
                }
            }
        }
    }

    class CategoriesHolder(private val itemBinding: MealItemRvBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(meal: MealModel) {

            itemBinding.mealNameTv.text = meal.name
            Log.d("mealadapter", meal.toString())
            Glide.with(itemBinding.root)
                .load(meal.image)
                .override(400)
                .centerInside()
                .into(itemBinding.mealImageView)
            setFadeAnimation(itemBinding.root)
            itemBinding.root.setOnClickListener {
                Navigation.findNavController(itemBinding.root).navigate(
                    R.id.action_mealsFromCategoryFragment_to_mealDetailFragment,
                    bundleOf("meal_id" to meal.id)
                )
            }
        }

        private fun setFadeAnimation(view: View) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 800
            view.startAnimation(anim)
        }
    }
}