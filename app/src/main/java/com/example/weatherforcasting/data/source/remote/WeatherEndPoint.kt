package com.banquemisr.challenge05.data.source.remote

import com.example.weatherforcasting.data.model.CitiesResponse
import com.example.weatherforcasting.data.model.WeatherResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface WeatherEndPoint {
    @GET(GET_WEATHER) // movie list
    suspend fun getWeatherInfo(@Path("id") id: Double = VERSION_PATH,@QueryMap hashMap: HashMap<String,Any>
    ): ApiResponse<WeatherResponse>

    companion object {
        const val GET_WEATHER="{id}/forecast"
        const val VERSION_PATH = 2.5
    }
}