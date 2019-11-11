package com.bahadireray.weatherapp.data.remote


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {


    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://api.openweathermap.org/"

    val newInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val okHttpClient = OkHttpClient.Builder().apply {
                    addInterceptor(interceptor)
                }.build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}