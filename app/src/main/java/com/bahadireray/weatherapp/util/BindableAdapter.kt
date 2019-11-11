package com.bahadireray.weatherapp.util

interface BindableAdapter<T> {
    fun setData(data: MutableList<T>)
}