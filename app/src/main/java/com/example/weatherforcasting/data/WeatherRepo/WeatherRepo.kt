package com.example.weatherforcasting.data.cititesrepo

import com.example.weatherforcasting.data.model.WeatherDataMigration

interface WeatherRepo {
    suspend fun getWeatherResponse(cityId: Int, hashMap: HashMap<String, Any>): WeatherDataMigration
    suspend fun getWeatherFromLocal(cityId: Int): WeatherDataMigration
}