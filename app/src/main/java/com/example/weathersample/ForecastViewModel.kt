package com.example.weathersample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForecastViewModel(private val api: Api) : ViewModel() {

    val mainLiveData = MutableLiveData<ForecastResponse>()

    fun forecast(id: Int) {
        viewModelScope.launch {
            val results = api.forecast(id)
            if (results.isSuccessful){
                results.body()?.let {
                    mainLiveData.postValue(it)
                }
            }
        }
    }
}