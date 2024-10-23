package com.example.weatherforcasting.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.data.RoomConverter
import com.example.weatherforcasting.data.model.WeatherItem
import com.example.weatherforcasting.data.source.local.WeatherDao
import com.example.weatherforcasting.data.source.local.dao.CityDao

@Database(entities = [City::class, WeatherItem::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao



}