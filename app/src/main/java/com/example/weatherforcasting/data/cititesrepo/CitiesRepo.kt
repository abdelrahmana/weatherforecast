package com.example.weatherforcasting.data.cititesrepo

import com.example.weatherforcasting.data.model.CitiesResponse
import com.example.weatherforcasting.data.model.City
import com.skydoves.sandwich.ApiResponse

interface CitiesRepo {
    suspend fun getCities() : ArrayList<City>
    suspend fun getCitiesRepoFromDao(
    ) : List<City>
}