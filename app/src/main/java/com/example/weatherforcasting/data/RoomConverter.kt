package com.example.weatherforcasting.data
import androidx.room.TypeConverter
import com.example.weatherforcasting.data.model.Clouds
import com.example.weatherforcasting.data.model.Coord
import com.example.weatherforcasting.data.model.Main
import com.example.weatherforcasting.data.model.Sys
import com.example.weatherforcasting.data.model.Weather
import com.example.weatherforcasting.data.model.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {
    @TypeConverter
    fun saveBelongCollection(listOfString: Coord?): String? {
        return Gson().toJson(listOfString)
    }
    @TypeConverter
    fun getBelongCollection(listOfString: String?): Coord? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<Coord?>() {}.type
        )
    }
    @TypeConverter
    fun saveMain(listOfString: Main?): String? {
        return Gson().toJson(listOfString)
    }
    @TypeConverter
    fun getMainCollection(listOfString: String?): Main? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<Main?>() {}.type
        )
    }
    @TypeConverter
    fun saveClouds(listOfString: Clouds?): String? {
        return Gson().toJson(listOfString)
    }
    @TypeConverter
    fun getCloudsCollection(listOfString: String?): Clouds? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<Clouds?>() {}.type
        )
    }
    @TypeConverter
    fun saveSys(listOfString: Sys?): String? {
        return Gson().toJson(listOfString)
    }
    @TypeConverter
    fun getSys(listOfString: String?): Sys? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<Sys?>() {}.type
        )
    }
    @TypeConverter
    fun saveWindList(listOfString: Wind?): String? {
        return Gson().toJson(listOfString)
    }
    @TypeConverter
    fun getWind(listOfString: String?): Wind? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<Wind?>() {}.type
        )
    }
    @TypeConverter
    fun saveStringList(listOfString: List<Weather>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun getStringList(listOfString: String?): List<Weather>? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<List<Weather>?>() {}.type
        )
    }




}