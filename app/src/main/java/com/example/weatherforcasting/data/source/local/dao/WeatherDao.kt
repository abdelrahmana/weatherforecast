package com.example.weatherforcasting.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforcasting.data.model.WeatherItem

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherItem")
    suspend fun getAll(): List<WeatherItem>

    @Query("SELECT * FROM WeatherItem WHERE cityMigratedId = :cityId /*LIMIT 1*/")
    suspend fun getWeatherInfo(cityId: Int): List<WeatherItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherList(weatherList: List<WeatherItem>)
}