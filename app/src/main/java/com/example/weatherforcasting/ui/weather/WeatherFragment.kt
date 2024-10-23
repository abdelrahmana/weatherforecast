package com.example.weatherforcasting.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforcasting.R
import com.example.weatherforcasting.data.OnlineConnection
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.data.model.WeatherItem
import com.example.weatherforcasting.databinding.WeatherFragmentBinding
import com.example.weatherforcasting.ui.weather.adaptor.SpinnerCustomAdpter
import com.example.weatherforcasting.ui.weather.adaptor.WeatherAdaptor
import com.example.weatherforcasting.util.updateVisibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.HashMap

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val viewModel: WeatherViewModel by viewModels()
    private var arrayListOfCities = ArrayList<City>()
    private var arrayListOfWeatherItem = ArrayList<WeatherItem>()
    private var weatherAdaptor : WeatherAdaptor? = null
    private var binding: WeatherFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = WeatherFragmentBinding.inflate(layoutInflater, container, false)
        viewModel.getCities(OnlineConnection())
        setViewObserver()
        observeChangeSpinner()
        setUpWeather()
        return binding?.root
    }

    private fun setUpWeather() {
        weatherAdaptor = WeatherAdaptor(requireContext(),arrayListOfWeatherItem)
        binding!!.weatherRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = weatherAdaptor
        }
        binding?.retryButton?.setOnClickListener {
            getWeatherRequest()
        }
    }

    private fun setViewObserver() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.weatherDataStateFlow.collectLatest {
                it?.let {
                    it.error?.let { // in case there is an error in fetch data
                        binding?.noResult?.updateVisibility(View.VISIBLE)
                    }
                    binding?.offlineMode?.updateVisibility(if (it.resultOffline!=null &&!it.result.isNullOrEmpty())View.VISIBLE else View.GONE)
                    it.result?.let {
                        arrayListOfWeatherItem.clear()
                        weatherAdaptor?.updateList(it)

                    }
                        //bindViewWithData(binding,it)
                }
            }
        }
        viewModel.networkLoader.observe(viewLifecycleOwner, Observer {
            it?.let { progress ->
                binding?.progress?.visibility = it
            }
        })
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.cityStateFlow.collectLatest {
                it?.let {
                    arrayListOfCities.clear()
                    arrayListOfCities.addAll(it)
                    val adaptor = SpinnerCustomAdpter(
                        activity,
                        R.layout.spinner_city_one_item,
                        arrayListOfCities
                    )
                    binding?.spinnerFrom?.adapter = adaptor
                }
            }
        }
    }
    private fun observeChangeSpinner() {
        binding?.containerClickFrom?.setOnClickListener{
            binding?.spinnerFrom?.performClick()
        }
        binding?.spinnerFrom?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getWeatherRequest()
            }
        }
    }

    private fun getWeatherRequest() {
        binding?.noResult?.updateVisibility(View.GONE) // hide no result view weathers
        arrayListOfCities.get(binding?.spinnerFrom?.selectedItemPosition?:0).apply {
            binding?.valueSelectedFrom?.text = arrayListOfCities.get(binding?.spinnerFrom?.selectedItemPosition?:0).cityNameEn
            viewModel.getWeatherData(OnlineConnection(),id?:0, HashMap<String, Any>().also {
                it[LAT] = lat?:0.0
                it[LON] = lon?:0.0
            })
        }
    }


    companion object {
        const val LAT = "lat"
        const val LON = "lon"

    }
}