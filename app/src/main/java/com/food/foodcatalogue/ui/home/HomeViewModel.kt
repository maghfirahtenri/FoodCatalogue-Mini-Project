package com.kocaksapp.foodcatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kocaksapp.foodcatalogue.data.network.ApiConfig
import com.kocaksapp.foodcatalogue.data.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val _listMeals = MutableLiveData<List<RandomItem>>()
    val listMeals : LiveData<List<RandomItem>> get() = _listMeals

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun getMeals() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getRandomFoods()

        client.enqueue(object : Callback<RandomResponse>{
            override fun onResponse(call: Call<RandomResponse>, response: Response<RandomResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _listMeals.value = response.body()?.meals as List<RandomItem>
                }
            }

            override fun onFailure(call: Call<RandomResponse>, t: Throwable) {
                _isLoading.value = false
                _message.value = t.localizedMessage
            }

        })
    }
}