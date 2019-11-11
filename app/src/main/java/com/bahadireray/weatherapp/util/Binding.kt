package com.bahadireray.weatherapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


object Binding {
    @JvmStatic
    @BindingAdapter("data")
    fun <T> RecyclerView.setRecyclerViewProperties(data: MutableList<T>) {
        if (this.adapter is BindableAdapter<*>) {
            (this.adapter as BindableAdapter<T>).setData(data)

        }
    }
}
