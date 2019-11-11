package com.bahadireray.weatherapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bahadireray.weatherapp.data.local.WeatherData

class ListViewModel : ViewModel() {

    private val _items =
        MutableLiveData<MutableList<WeatherData>>().apply { value = mutableListOf() }
    val items: LiveData<MutableList<WeatherData>> = _items


    fun setData(weatherData: MutableList<WeatherData>?) {
        if (!weatherData.isNullOrEmpty()){
            _items.value?.addAll(weatherData)
        }
    }
}
