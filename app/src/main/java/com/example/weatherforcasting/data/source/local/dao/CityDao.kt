package com.example.weatherforcasting.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforcasting.data.model.City

@Dao
interface CityDao {
    @Query("SELECT * FROM City")
    suspend fun getAll(): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(user: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(user: List<City>)
}