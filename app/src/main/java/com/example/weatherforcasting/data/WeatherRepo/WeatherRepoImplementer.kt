package com.example.weatherforcasting.data.cititesrepo

import com.banquemisr.challenge05.data.source.remote.WeatherEndPoint
import com.example.weatherforcasting.R
import com.example.weatherforcasting.data.model.WeatherDataMigration
import com.example.weatherforcasting.data.model.WeatherItem
import com.example.weatherforcasting.data.source.AppDataBase
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepoImplementer @Inject constructor(
    val localDataBase: AppDataBase, val webService: WeatherEndPoint
) : WeatherRepo {
   override suspend fun getWeatherFromLocal(
        cityId: Int
    ): WeatherDataMigration {
        val weatherDataMigration = WeatherDataMigration(ArrayList())
        try {
            val weatherDao = localDataBase.weatherDao()
            val weatherItems: List<WeatherItem> = weatherDao.getWeatherInfo(cityId)
            if (weatherItems.isEmpty())
                weatherDataMigration.error = R.string.no_data
            weatherDataMigration.result?.addAll(weatherItems)
            weatherDataMigration.resultOffline = R.string.warning_you_are_offline
        } catch (e: Exception) {
            weatherDataMigration.error = R.string.error_happened
        }
        return weatherDataMigration
    }

    override suspend fun getWeatherResponse(
        cityId: Int,
        hashMap: HashMap<String, Any>
    ): WeatherDataMigration {
        val res = webService.getWeatherInfo(hashMap = hashMap)
        val weatherDao = localDataBase.weatherDao()
        var weatherDataMigration = WeatherDataMigration(ArrayList())
        res.onSuccess {
            data?.apply {
                if (!list.isNullOrEmpty()) {
                    list.forEach {
                        it.cityMigratedId = cityId
                    }
                    weatherDataMigration.result?.addAll(list)
                    try {
                        weatherDao.insertWeatherList(list)
                    } catch (_: Exception) {
                    }
                }
            }
        }
        if (weatherDataMigration.result?.isEmpty() == true)
            weatherDataMigration= getWeatherFromLocal(cityId) // get from api
        return weatherDataMigration
    }


}

