package com.kocaksapp.foodcatalogue.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RandomResponse(

	@field:SerializedName("meals")
	val meals: List<RandomItem>? = null
) : Parcelable

@Parcelize
data class RandomItem(

	@field:SerializedName("idMeal")
	val idMeal: String? = null,

	@field:SerializedName("strMeal")
	val strMeal: String? = null,

	@field:SerializedName("strCategory")
	val strCategory: String? = null,

	@field:SerializedName("strTags")
	val strTags: String? = null,

	@field:SerializedName("strMealThumb")
	val strMealThumb: String? = null,

	@field:SerializedName("strArea")
	val strArea: String? = null,

	@field:SerializedName("strInstructions")
	val strInstructions: String? = null
) : Parcelable
