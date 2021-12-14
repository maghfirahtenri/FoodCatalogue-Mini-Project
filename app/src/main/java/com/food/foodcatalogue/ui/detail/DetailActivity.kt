package com.kocaksapp.foodcatalogue.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kocaksapp.foodcatalogue.data.response.RandomItem
import com.kocaksapp.foodcatalogue.data.response.MealsItem
import com.kocaksapp.foodcatalogue.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val PASSING_DATA_RANDOM_ITEM = "DATA_RANDOM"
        const val PASSING_DATA_MEALS_ITEM = "DATA_ITEM_MEALS"
    }

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private var mealId : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataRandom = intent.getParcelableExtra<RandomItem>(PASSING_DATA_RANDOM_ITEM)
        val dataMealsItem = intent.getParcelableExtra<MealsItem>(PASSING_DATA_MEALS_ITEM)

        if (dataRandom == null) {
            mealId = dataMealsItem?.idMeal
            binding.btnShare.setOnClickListener {
                var intent = Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT, """
                        Pesan makanan ${dataMealsItem?.strMeal}!
                        ${dataMealsItem?.strMeal}
                        ${dataMealsItem?.strMealThumb}
                    """.trimIndent())
                    this.type = "text/plain"
                }
                startActivity(intent)
            }
        } else if (dataMealsItem == null) {
            mealId = dataRandom.idMeal
        }

        observingValue()
    }

    private fun observingValue() {
        detailViewModel.isLoading.observe(this, Observer {
            if (it == true) {
            }
        })

        detailViewModel.setSelectedMeal(mealId)
        detailViewModel.getDetailMeal()

        detailViewModel.detailMeal.observe(this, {
            for (meal in it) {
                Glide.with(this)
                    .load(meal.strMealThumb)
                    .into(binding.ivPoster)

                binding.tvTitle.text = meal.strMeal
                binding.tvDesc.text = meal.strInstructions?.substring(0, 300).toString()
            }
        })
    }
}