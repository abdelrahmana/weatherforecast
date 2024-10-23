package com.example.weatherforcasting.ui.weather

import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.weatherforcasting.base.BaseViewModel
import com.example.weatherforcasting.data.InternetStatus
import com.example.weatherforcasting.data.cititesrepo.CitiesRepo
import com.example.weatherforcasting.data.cititesrepo.WeatherRepo
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.data.model.WeatherDataMigration
import com.example.weatherforcasting.data.model.WeatherItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class WeatherViewModel @Inject constructor(
    private val repoCities: CitiesRepo,
    private val weatherRepo: WeatherRepo
) :
    BaseViewModel() {
    private val _cityMutable = MutableStateFlow<List<City>?>(null)
    val cityStateFlow: StateFlow<List<City>?> = _cityMutable
    private val _weatherDataMutable = MutableStateFlow<WeatherDataMigration?>(null)
    val weatherDataStateFlow: StateFlow<WeatherDataMigration?> = _weatherDataMutable
    fun getWeatherData(
        internetStatus: InternetStatus,
        cityId: Int,
        queryMap: HashMap<String, Any>
    ) {
        setNetworkLoader(View.VISIBLE)
        viewModelScope.launch(Dispatchers.IO) {
            val result = internetStatus.getWeather(weatherRepo, cityId, queryMap)
            setNetworkLoader(View.GONE)
            with(result) {
                this@with.result?.let { it ->
                    _weatherDataMutable.emit(result) // to cover the case of offline
                }

               /* error?.let { it ->
                    setError(it)
                }*/
            }
        }
    }

    fun getCities(internetStatus: InternetStatus) {
        setNetworkLoader(View.VISIBLE)
        viewModelScope.launch(Dispatchers.IO) {
            _cityMutable.emit(internetStatus.getCities(repoCities))
            setNetworkLoader(View.GONE)
        }
    }
}