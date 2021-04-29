package com.example.weathersample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForecastViewModel(private val api: Api) : ViewModel() {

    val mainLiveData = MutableLiveData<ForecastResponse>()
    val errorLiveData = MutableLiveData<Unit>()

    fun getForecast(id: Int) {
        viewModelScope.launch {
            try {
                val results = api.forecast(id)
                if (results.isSuccessful) {
                    results.body()?.let {
                        mainLiveData.postValue(it)
                    }
                }
            } catch (e: Exception) {
                errorLiveData.postValue(Unit)
            }
        }
    }
}