package com.kocaksapp.foodcatalogue.data.response

import com.google.gson.annotations.SerializedName

data class DetailMealResponse(

	@field:SerializedName("meals")
	val meals: List<DetailMealsItem?>? = null
)

data class DetailMealsItem(

	@field:SerializedName("strCategory")
	val strCategory: String? = null,

	@field:SerializedName("strMealThumb")
	val strMealThumb: String? = null,

	@field:SerializedName("strTags")
	val strTags: String? = null,

	@field:SerializedName("strArea")
	val strArea: String? = null,

	@field:SerializedName("idMeal")
	val idMeal: String? = null,

	@field:SerializedName("strMeal")
	val strMeal: String? = null,

	@field:SerializedName("strInstructions")
	val strInstructions: String? = null
)
