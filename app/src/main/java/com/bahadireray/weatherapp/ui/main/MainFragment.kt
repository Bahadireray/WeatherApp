package com.bahadireray.weatherapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bahadireray.weatherapp.R
import com.bahadireray.weatherapp.databinding.MainFragmentBinding
import com.bahadireray.weatherapp.data.local.DatabaseHelper
import com.bahadireray.weatherapp.util.GPSTracker


class MainFragment : Fragment() {


    var db: DatabaseHelper? = null

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private lateinit var viewDataBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding =
            MainFragmentBinding.bind(inflater.inflate(R.layout.main_fragment, container, false))
                .apply {
                    this.viewmodel = viewModel
                    lifecycleOwner = this@MainFragment
                }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_listFragent -> {
                findNavController().navigate(R.id.action_mainFragment_to_listFragment)
                true
            }
            R.id.action_save -> {
                saveData()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun saveData() {
        viewModel.getItem()?.let {
            db?.insertData(it.name, it.wind.deg.toString())
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            db = DatabaseHelper(it)
        }

        val gps = GPSTracker(context!!)
        val location = gps.getLocation()

        Log.i("Location : ", location.toString())

        viewModel.getWeatherData(
            "41.01384",
            "28.94966"
        )



        viewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }


}
