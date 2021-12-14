package com.kocaksapp.foodcatalogue.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMealItem(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strCategory: String? = null,
    val strMealThumb: String? = null,
    val strTags: String? = null,
    val strArea: String? = null,
    val strInstructions: String? = null
) : Parcelable