package com.bahadireray.weatherapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahadireray.weatherapp.data.local.WeatherData
import com.bahadireray.weatherapp.databinding.ListItemHistoryBinding
import com.bahadireray.weatherapp.util.BindableAdapter


class ListAdapter : RecyclerView.Adapter<ListAdapter.TasksViewHolder>(),
    BindableAdapter<WeatherData> {

    private var dataList= mutableListOf<WeatherData>()
    override fun setData(data: MutableList<WeatherData>) {
        this.dataList=data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder(ListItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class TasksViewHolder(private val binding: ListItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: WeatherData) {


            binding.itemModel=data
            binding.executePendingBindings()

        }

    }

    private var onItemClicked: OnItemClickedListener? = null

    interface OnItemClickedListener {
        fun onItemClicked(id: String)
    }

    fun setOnClickListener(onClick: (OnItemClickedListener) -> Unit) {

    }
}