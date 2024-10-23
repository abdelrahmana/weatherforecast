package com.banquemisr.challenge05.data.source.remote

import com.example.weatherforcasting.data.model.CitiesResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface CityEndPoint {
    @GET(GET_Cities_LIST) // movie list
    suspend fun getCitiesFromServer(
    ): ApiResponse<CitiesResponse>

    companion object {
        const val GET_Cities_LIST="uploads/cities.json"
    }
}