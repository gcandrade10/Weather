package com.example.weathersample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel(private val api: Api) : ViewModel() {

    val mainLiveData = MutableLiveData<List<Result>>()

    fun search(query: String) {
        viewModelScope.launch {
            val results = api.search(query)
            if (results.isSuccessful){
                results.body()?.let {
                    mainLiveData.postValue(it)
                }
            }
        }
    }
}