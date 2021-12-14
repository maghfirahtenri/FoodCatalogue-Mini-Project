package com.kocaksapp.foodcatalogue.ui.home.category.vegetarian

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kocaksapp.foodcatalogue.data.network.ApiConfig
import com.kocaksapp.foodcatalogue.data.response.FoodsResponse
import com.kocaksapp.foodcatalogue.data.response.MealsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VegetarianMainViewModel : ViewModel(){
    private val _listMeals = MutableLiveData<List<MealsItem>>()
    val listMeals: LiveData<List<MealsItem>> get() = _listMeals

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun getMeals() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListFoods()

        client.enqueue(object : Callback<FoodsResponse> {
            override fun onResponse(call: Call<FoodsResponse>, response: Response<FoodsResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _listMeals.value = response.body()?.meals as List<MealsItem>
                }
            }

            override fun onFailure(call: Call<FoodsResponse>, t: Throwable) {
                _isLoading.value = false
                _message.value = t.localizedMessage
            }

        })

    }
}