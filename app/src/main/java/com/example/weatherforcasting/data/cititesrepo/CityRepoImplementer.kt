package com.example.weatherforcasting.data.cititesrepo

import com.banquemisr.challenge05.data.source.remote.CityEndPoint
import com.example.weatherforcasting.data.model.CitiesResponse
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.data.source.AppDataBase
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onSuccess
import javax.inject.Inject

class CityRepoImplementer @Inject constructor(
    val localDataBase: AppDataBase, val webService: CityEndPoint
) : CitiesRepo {
   override suspend fun getCitiesRepoFromDao(
    ) : List<City>  {
        val citiesList = ArrayList<City>()
        try {
            val citiesDao = localDataBase.cityDao()
            val cities: List<City>
             = citiesDao.getAll()
            citiesList.addAll(cities)
        } catch (e: Exception) {

        }
       return citiesList
    }

    override suspend fun getCities(): ArrayList<City> {
        val res = webService.getCitiesFromServer()
        val cityDao = localDataBase.cityDao()
        var resultCities = ArrayList<City>()
        res.onSuccess {
            if (!data?.cities.isNullOrEmpty())
                try {
                    resultCities.addAll(data?.cities?: ArrayList())
                    cityDao.insertCities(data?.cities ?: ArrayList())
                } catch (_: Exception) {
                }

        }
        if (resultCities.isEmpty())
            resultCities.addAll(getCitiesRepoFromDao())
        return resultCities
    }


}

