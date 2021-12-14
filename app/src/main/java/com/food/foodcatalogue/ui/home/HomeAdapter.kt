package com.kocaksapp.foodcatalogue.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kocaksapp.foodcatalogue.data.response.MealsItem
import com.kocaksapp.foodcatalogue.databinding.ListAllMealsBinding
import com.kocaksapp.foodcatalogue.ui.detail.DetailActivity

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {

    var mealsList = ArrayList<MealsItem>()
        set(meal) {
            if (meal.size > 0) {
                this.mealsList.clear()
            }
            this.mealsList.addAll(meal)

            notifyDataSetChanged()
        }

    private lateinit var binding: ListAllMealsBinding

    inner class ListViewHolder(private val binding: ListAllMealsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meals: MealsItem){
            Glide.with(itemView.context)
                .load(meals.strMealThumb)
                .into(binding.ivEvent)

            binding.tvTitle.text = meals.strMeal
            binding.tvDateTime.text = meals.idMeal

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                    .putExtra(DetailActivity.PASSING_DATA_MEALS_ITEM, meals)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ListViewHolder {
        binding = ListAllMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ListViewHolder, position: Int) {
        holder.bind(mealsList[position])
    }

    override fun getItemCount(): Int = mealsList.size
}