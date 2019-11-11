package com.bahadireray.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bahadireray.weatherapp.data.remote.IDataService
import com.bahadireray.weatherapp.data.remote.RetrofitClient
import com.bahadireray.weatherapp.data.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val apiService = RetrofitClient.newInstance!!.create(
        IDataService::class.java
    )


    private val _item = MutableLiveData<WeatherModel>()
    val item: LiveData<WeatherModel> = _item

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getItem(): WeatherModel? {
        return item.value
    }

    fun getWeatherData(lat: String, lon: String) {

        val call = apiService.getWearherWithLoc(
            lat, lon, "0427b2284f3348e9734d0774ce128af4"
        )
        call?.enqueue(object : Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    _item.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                _error.value = t.message
            }
        })
    }
}
