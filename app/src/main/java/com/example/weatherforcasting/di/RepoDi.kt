package com.example.weatherforcasting.di

import com.banquemisr.challenge05.data.source.remote.CityEndPoint
import com.banquemisr.challenge05.data.source.remote.WeatherEndPoint
import com.example.weatherforcasting.data.cititesrepo.CitiesRepo
import com.example.weatherforcasting.data.cititesrepo.CityRepoImplementer
import com.example.weatherforcasting.data.cititesrepo.WeatherRepo
import com.example.weatherforcasting.data.cititesrepo.WeatherRepoImplementer
import com.example.weatherforcasting.data.source.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class, FragmentComponent::class,
    ActivityComponent::class)
class RepoDi {
    @Provides
    fun getRepoWeather(
        localDataBase: AppDataBase, endPoints: WeatherEndPoint
    ): WeatherRepo {
        return  WeatherRepoImplementer(localDataBase,endPoints)
    }
    @Provides
    fun getRepoCity(
        localDataBase: AppDataBase, endPoints: CityEndPoint
    ): CitiesRepo {
        return  CityRepoImplementer(localDataBase,endPoints)
    }


}