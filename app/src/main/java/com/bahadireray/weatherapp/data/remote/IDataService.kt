package com.bahadireray.weatherapp.data.remote

import com.bahadireray.weatherapp.data.model.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataService {


    @GET("/data/2.5/weather")
    fun getWearherWithLoc(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appid: String): Call<WeatherModel>

}