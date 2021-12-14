package com.kocaksapp.foodcatalogue.data.network

import com.kocaksapp.foodcatalogue.data.response.DetailMealResponse
import com.kocaksapp.foodcatalogue.data.response.FoodsResponse
import com.kocaksapp.foodcatalogue.data.response.RandomResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/json/v1/1/filter.php?c=Vegetarian")
    fun getListFoods(): Call<FoodsResponse>

    @GET("api/json/v1/1/random.php")
    fun getRandomFoods(): Call<RandomResponse>

    @GET("api/json/v1/1/lookup.php")
    fun getDetailMeal(@Query("i") id: String): Call<DetailMealResponse>
}