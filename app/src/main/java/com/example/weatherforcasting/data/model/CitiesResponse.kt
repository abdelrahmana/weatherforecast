package com.example.weatherforcasting.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
data class CitiesResponse(
    @SerializedName("cities")
    val cities: List<City>?
)
@Entity
data class City(
    @SerializedName("cityNameAr")
    @ColumnInfo
    val cityNameAr: String?,
    @ColumnInfo
    @SerializedName("cityNameEn")
    val cityNameEn: String?,
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,
    @ColumnInfo
    @SerializedName("lat")
    val lat: Double?,
    @ColumnInfo
    @SerializedName("lon")
    val lon: Double?
)