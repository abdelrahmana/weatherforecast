package com.example.weatherforcasting.data

import com.example.weatherforcasting.data.cititesrepo.CitiesRepo
import com.example.weatherforcasting.data.cititesrepo.WeatherRepo
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.data.model.WeatherDataMigration

interface InternetStatus {
    suspend fun getWeather(
        repoWeather: WeatherRepo,
        cityId: Int,
        hashMap: HashMap<String, Any>
    ): WeatherDataMigration

    suspend fun getCities(
        repoWeather: CitiesRepo
    ): ArrayList<City>
}

class OnlineConnection() : InternetStatus {
    override suspend fun getWeather(
        repoWeather: WeatherRepo,
        cityId: Int,
        hashMap: HashMap<String, Any>
    ): WeatherDataMigration {
        return repoWeather.getWeatherResponse(cityId, hashMap)
    }

    override suspend fun getCities(
        repoWeather: CitiesRepo,
    ): ArrayList<City> {
        return repoWeather.getCities()

    }
}