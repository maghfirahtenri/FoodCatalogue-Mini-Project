package com.kocaksapp.foodcatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kocaksapp.foodcatalogue.data.network.ApiConfig
import com.kocaksapp.foodcatalogue.data.response.DetailMealResponse
import com.kocaksapp.foodcatalogue.data.response.DetailMealsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    var mealId: String? = ""

    private val _detailMeal = MutableLiveData<List<DetailMealsItem>>()
    val detailMeal : MutableLiveData<List<DetailMealsItem>> get() = _detailMeal

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun setSelectedMeal(mealId: String?) {
        this.mealId = mealId
    }

    fun getDetailMeal() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getDetailMeal("$mealId")
        client.enqueue(object : Callback<DetailMealResponse> {
            override fun onResponse(
                call: Call<DetailMealResponse>,
                response: Response<DetailMealResponse>
            ) {
                _isLoading.value = false
                _detailMeal.value = response.body()?.meals as List<DetailMealsItem>
            }

            override fun onFailure(call: Call<DetailMealResponse>, t: Throwable) {
                _isLoading.value = false

            }

        })
    }
}