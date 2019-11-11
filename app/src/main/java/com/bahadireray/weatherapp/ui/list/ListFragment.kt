package com.bahadireray.weatherapp.ui.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bahadireray.weatherapp.R
import com.bahadireray.weatherapp.data.local.DatabaseHelper
import com.bahadireray.weatherapp.databinding.ListFragmentBinding

class ListFragment : Fragment() {


    private var db: DatabaseHelper? = null
    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this).get(ListViewModel::class.java)
    }
    private lateinit var viewDataBinding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding =
            ListFragmentBinding.bind(inflater.inflate(R.layout.list_fragment, container, false))
                .apply {
                    this.viewmodel = viewModel
                    lifecycleOwner = this@ListFragment
                }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        context?.let {
            db = DatabaseHelper(it)
            viewModel.setData(db?.readData())
        }


    }

    private fun setupListAdapter() {
        viewDataBinding.historyList.adapter = ListAdapter()

    }


}
